/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarCodeGenerator;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 * FXML Controller class
 *
 * @author Omskamate
 */
public class BarCodeGeneratorController implements Initializable {

    File barcodeFile;
    @FXML
    private JFXTextField barcodePath;
    @FXML
    private JFXButton btn;
    @FXML
    private JFXTextField id;
    @FXML
    private Label code;

    @FXML
    private ImageView img;

    @FXML
    void backAction(ActionEvent event) {
        try {
            Stage stagetemp = (Stage) id.getScene().getWindow();
            stagetemp.close();

            Stage stage = new Stage();
            AnchorPane root = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
            Scene scene = new Scene(root, 1200, 600);
            // stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BarCodeGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*  barcodeFile= new File(barcodePath.getText()+"\\"+"LibraryBarCode");
        if(!barcodeFile.exists())
        {
            barcodeFile.mkdir();
        }*/

    }

    private void code() throws FileNotFoundException, IOException, BadElementException, DocumentException {
        Code128Bean code128 = new Code128Bean();
        code128.setHeight(15f);
        code128.setModuleWidth(0.3);
        code128.setQuietZone(10);
        code128.doQuietZone(true);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 400, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        code128.generateBarcode(canvas, id.getText());
        canvas.finish();

        try (FileOutputStream fos = new FileOutputStream("barcode.png")) {
            fos.write(baos.toByteArray());
            fos.flush();
        }

        Image png = Image.getInstance(baos.toByteArray());
        png.setAbsolutePosition(0, 705);
        png.scalePercent(25);

        javafx.scene.image.Image newimage = new javafx.scene.image.Image("file:barcode.png");
        img.setImage(newimage);
        Document document;
        document = new Document();
        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        for (int aw = 0; aw < 27; aw++) {
            Paragraph p = new Paragraph("        Product Name");
            p.add("\n        Price:500");
//           p.add(createImageCell(png));
            PdfPTable intable = new PdfPTable(1);
            intable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            intable.addCell(p);
            intable.addCell(png);
            intable.getDefaultCell().setBorder(0);

            table.addCell(intable);
        }
//        table.setBorder(Border.NO_BORDER);
        Paragraph p = new Paragraph("Product Name");
        p.add("\nPrice:500");
        p.add(png);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(barcodeFile.getAbsolutePath() + "\\" + id.getText().trim() + ".pdf"));
        document.open();
//        document.add();
        document.add(table);
        document.close();

        writer.close();

    }

    @FXML
    void browserAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage=new Stage();
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory == null) {
            //No Directory selected
        } else {
          barcodePath.setText(selectedDirectory.getPath());
        }
    }

    @FXML
    void barcodeAction(ActionEvent event) {
        try {
            // DialogBox.DialogBox.showDialog(DialogBox.DialogBox.change_pass_success);
            if (id.getText().trim().isEmpty()) {

                DialogBox.DialogBox.showDialog(DialogBox.DialogBox.field_empty);

            } else {
                if (barcodePath.getText().trim().isEmpty()) {
                    DialogBox.DialogBox.showDialog(DialogBox.DialogBox.field_empty);
                } else {
                    barcodeFile = new File(barcodePath.getText());
                    if (!barcodeFile.exists()) {
                        barcodeFile.mkdir();
                    }
                    code();
                }
            }
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(BarCodeGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
