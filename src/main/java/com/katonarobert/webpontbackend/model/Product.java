package com.katonarobert.webpontbackend.model;

import org.apache.avalon.framework.configuration.ConfigurationException;
import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import static com.katonarobert.webpontbackend.util.EAN8Barcode.buildCfg;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    @Column
    private int productAmount;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "product")
    private Barcode barCode;

    @Column
    private Status status;

    public Product() {
    }

    public Product(String productName, int productAmount) {
        this.productName = productName;
        this.productAmount = productAmount;
        this.barCode = createBarcode(productName);
        this.status = Status.ACTIVE;
    }

    private Barcode createBarcode(String productName) {
        String rand = String.valueOf((new Random()).nextInt(9000000) + 1000000);

        BarcodeUtil util = BarcodeUtil.getInstance();
        BarcodeGenerator gen = null;
        try {
            try {
                gen = util.createBarcodeGenerator(buildCfg("ean-8"));
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        } catch (BarcodeException e) {
            e.printStackTrace();
        }

        OutputStream fout = null;
        try {
            fout = new FileOutputStream(new File("./src/barcodes/EAN8_barcode_" + rand + productName + ".jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int resolution = 200;
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                fout, "image/jpeg", resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        gen.generateBarcode(canvas, rand);
        try {
            canvas.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Barcode("EAN8_barcode_" + productName + ".jpg", this);
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public void increaseProductAmount(boolean upOrDown) {
        if (upOrDown) {
            productAmount++;
        } else {
            if (productAmount > 0) {
                productAmount--;
            }
        }
    }

    public Status getStatus() {
        return status;
    }

    // TODO: Delete method without real deletion
    //    public void setStatus() {
    //        this.status = (status == Status.ACTIVE) ? Status.ACTIVE : Status.DISABLED;
    //    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productAmount=" + productAmount +
                '}';
    }
}
