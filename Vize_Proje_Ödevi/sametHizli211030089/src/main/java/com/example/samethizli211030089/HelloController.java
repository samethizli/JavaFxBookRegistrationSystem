package com.example.samethizli211030089;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Spinner<Integer> spnBasımYili;

    @FXML
    private ComboBox<String> cmbKitapDili;

    @FXML
    private ComboBox<String> cmbKitapSec;

    @FXML
    private ComboBox<String> cmbKitapTürü;

    @FXML
    private ComboBox<String> cmbYayınEvi;

    @FXML
    private DatePicker dtpYayınTarihi;

    @FXML
    private TextField txtBasımYili;

    @FXML
    private TextField txtIsbnNo;

    @FXML
    private TextField txtIsbnNoGoster;

    @FXML
    private TextField txtKitapAdi;

    @FXML
    private TextField txtKitapDili;

    @FXML
    private TextField txtKitapTürü;

    @FXML
    private TextField txtYayinEvi;

    @FXML
    private TextField txtYayinTarihi;

    @FXML
    private TextField txtYazarAdi;

    @FXML
    private TextField txtYazarAdiGoster;

    private ArrayList<Kitap> kitaplar = new ArrayList<>();

    @FXML
    void kitapKaydet(ActionEvent event){
    String KitapAdi = txtKitapAdi.getText();
    String YazarAdi = txtYazarAdi.getText();
    LocalDate yayinTarihi = dtpYayınTarihi.getValue();
    String KitapDili = cmbKitapDili.getValue();
    String KitapTürü = cmbKitapTürü.getValue();
    int Basımyili = spnBasımYili.getValue();
    String YayinEvi = cmbYayınEvi.getValue();



    if (txtIsbnNo.getText().isEmpty() || txtKitapAdi.getText().isEmpty())
    {
      Alert hatamesajı = new Alert(Alert.AlertType.ERROR);
      hatamesajı.setTitle("Hata");
      hatamesajı.setHeaderText("Adsoyad veya ISBN Numarası girilmedi...");
      hatamesajı.show();
      return;
    }
       int IsbnNo;
    try {
        IsbnNo = Integer.parseInt(txtIsbnNo.getText());

    }
    catch (NumberFormatException e){
        Alert hatamesajı = new Alert(Alert.AlertType.ERROR);
        hatamesajı.setTitle("Hata");
        hatamesajı.setHeaderText("Lütfen Daha kısa bir ISBN numarası giriniz...");
        hatamesajı.show();
        return;
    }

        for (int i = 0; i < kitaplar.size(); i++) {
            if (IsbnNo == kitaplar.get(i).getIsbnNo()){
                Alert hatamesaji = new Alert(Alert.AlertType.ERROR);
                hatamesaji.setTitle("Hata");
                hatamesaji.setHeaderText(kitaplar.get(i).getIsbnNo()+ " ISBN numaralı kitap kayıtlı...");
                hatamesaji.show();
                return;
            }
        }



    Kitap kitap = new Kitap(KitapAdi,YazarAdi,KitapDili ,
            KitapTürü,  YayinEvi,IsbnNo,Basımyili,yayinTarihi);
    kitaplar.add(kitap);

        try {
            FileOutputStream fos = new FileOutputStream("kitapbilgileri.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(kitaplar);
            fos.close();
            oos.close();
            Alert uyarımesajı = new Alert(Alert.AlertType.INFORMATION);
            uyarımesajı.setTitle("Bilgi");
            uyarımesajı.setHeaderText(kitap.getKitapAdi()+" kitabı kaydedildi...");
            uyarımesajı.show();

            cmbKitapSec.getItems().add(kitap.getKitapAdi());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void kitapSec(ActionEvent event) {
      int secilenkitap=cmbKitapSec.getSelectionModel().getSelectedIndex();

      if (secilenkitap < 0)
          return;;

          txtYazarAdiGoster.setText(String.valueOf(kitaplar.get(secilenkitap).getYazarAdi()));
          txtIsbnNoGoster.setText(String.valueOf(kitaplar.get(secilenkitap).getIsbnNo()));
          txtYayinTarihi.setText(String.valueOf(kitaplar.get(secilenkitap).getYayinTarihi()));
          txtKitapDili.setText(String.valueOf(kitaplar.get(secilenkitap).getKitapDili()));
          txtKitapTürü.setText(String.valueOf(kitaplar.get(secilenkitap).getKitapTürü()));
          txtBasımYili.setText(String.valueOf(kitaplar.get(secilenkitap).getBasımyili()));
          txtYayinEvi.setText(String.valueOf(kitaplar.get(secilenkitap).getYayinEvi()));

    }

    @FXML
    void kitapSil(ActionEvent event) {
     int secilenkitap = cmbKitapSec.getSelectionModel().getSelectedIndex();
     String kitapadi = kitaplar.get(secilenkitap).getKitapAdi();

     cmbKitapSec.getItems().remove(secilenkitap);
     txtBasımYili.clear();
     txtYayinEvi.clear();
     txtIsbnNoGoster.clear();
     txtKitapTürü.clear();
     txtKitapDili.clear();
     txtYayinTarihi.clear();
     txtYazarAdiGoster.clear();
     kitaplar.remove(secilenkitap);


        try {
            FileOutputStream fos = new FileOutputStream("kitapbilgileri.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(kitaplar);
            fos.close();
            oos.close();
            Alert bilgimesaji = new Alert(Alert.AlertType.INFORMATION);

            bilgimesaji.setTitle("Bilgi");
            bilgimesaji.setHeaderText(kitapadi+ " kitabı silindi...");
            bilgimesaji.show();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbKitapDili.setItems(FXCollections.observableArrayList("Türkçe",
                "Almanca","Fransızca","İngilizce","İspanyolca","Rusça","Portekizce"));
        cmbKitapTürü.setItems(FXCollections.observableArrayList("Dünya Klasikleri","Roman",
                "Kişisel Gelişim","Tarih","Bilim Kurgu","Korku-Gerilim","Polisiye","Fantastik","Eğlence-Mizah"));
        cmbYayınEvi.setItems(FXCollections.observableArrayList("Tübitak Yayınları","Can Yayınları",
                "Yapı Kredi Yayınları","Tudem Yayınevi","Ketebe Yayınevi",
                "Everest Yayınları","Say Yayınları","Sel Yayıncılık","Çıra Yayınları","Epsilon Yayınevi"));
        spnBasımYili.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900,2022,2010,1));

        try {
            FileInputStream fis = new FileInputStream("kitapbilgileri.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            kitaplar=(ArrayList<Kitap>)ois.readObject();
            ois.close();

            for (int i = 0; i < kitaplar.size(); i++) {
                cmbKitapSec.getItems().add(kitaplar.get(i).getKitapAdi());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        txtIsbnNo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtIsbnNo.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


    }
}
