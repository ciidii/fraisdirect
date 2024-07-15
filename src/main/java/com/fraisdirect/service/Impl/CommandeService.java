package com.fraisdirect.service.Impl;

import com.fraisdirect.entity.Commande;
import com.fraisdirect.entity.CommandeProduit;
import com.fraisdirect.entity.Produit;
import com.fraisdirect.repository.CommandeRepository;
import com.fraisdirect.repository.ProduitRepository;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Commande validerCommande(Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commanded non trouvée"));

        Map<String, Integer> produitsIndisponibles = new HashMap<>();

        for (CommandeProduit cp : commande.getProduits()) {
            Produit produit = cp.getProduit();
            int quantiteDemandee = cp.getQuantite();
            int quantiteEnStock = produit.getQuantiteEnStock();

            if (quantiteEnStock < quantiteDemandee) {
                produitsIndisponibles.put(produit.getNom(), quantiteDemandee - quantiteEnStock);
            }
        }

        if (!produitsIndisponibles.isEmpty()) {
            StringBuilder emailText = new StringBuilder("Bonjour cher(e) client(e),\n\nLes produits suivants ne sont pas disponibles en quantité suffisante :\n");
            produitsIndisponibles.forEach((nom, quantite) -> emailText.append(nom).append(": ").append(quantite).append(" manquant(s)\n"));
            emailText.append("\nCordialement,\nVotre Boutique");
            emailService.sendEmail(commande.getClientEmail(), "Disponibilité de votre commande", emailText.toString());
            commande.setStatut(0);
            commandeRepository.save(commande);
        } else {
            commande.setStatut(1);
            commandeRepository.save(commande);
            updateStock(commande); // Mettre à jour le stock après validation de la commande
            genererFacturePDF(commande);
        }
        return commande;
    }

    @Transactional
    public void updateStock(Commande commande) {
        for (CommandeProduit cp : commande.getProduits()) {
            Produit produit = cp.getProduit();
            int quantiteEnStock = produit.getQuantiteEnStock();
            produit.setQuantiteEnStock(quantiteEnStock - cp.getQuantite());
            produitRepository.save(produit);
        }
    }

    public ResponseEntity<InputStreamResource> genererFacturePDF(Commande commande) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Entreprise : FraisDiriect.com"));
            document.add(new Paragraph("Facture N: " + commande.getId()));
            document.add(new Paragraph("Email du client: " + commande.getClientEmail()));

            Table table = new Table(4);
            table.addCell(new Cell().add(new Paragraph("ProduitM")));
            table.addCell(new Cell().add(new Paragraph("Quantité")));
            table.addCell(new Cell().add(new Paragraph("Prix Unitaire")));
            table.addCell(new Cell().add(new Paragraph("Total")));

            double sommeTotale = 0;

            for (CommandeProduit cp : commande.getProduits()) {
                Produit produit = cp.getProduit();
                double prix = Double.parseDouble(produit.getPrix());
                int quantite = cp.getQuantite();
                double total = prix * quantite;

                table.addCell(new Cell().add(new Paragraph(produit.getNom())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(quantite))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(prix))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(total))));

                sommeTotale += total;
            }

            document.add(table);
            document.add(new Paragraph("Somme Totale: " + sommeTotale));
            document.add(new Paragraph("Merci de votre fidélité"));
            document.add(new Paragraph("À bientôt"));

            document.close();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=facture.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())));

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du PDF", e);
        }
    }

    @Transactional
    public Commande creerCommande(Commande commande) {
        for (CommandeProduit commandeProduit : commande.getProduits()) {
            commandeProduit.setCommande(commande);
        }

        return commandeRepository.save(commande);
    }
}
