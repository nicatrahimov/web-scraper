package group.aist;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    static String baseUrl = "https://turbo.az/autos";
    private static final ArrayList<Car> cars = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 5; i++) {
            String pageByPageNumberUrl = baseUrl + "?page=" + i;

            Document baseDocument = Jsoup.connect(pageByPageNumberUrl).get();
            Elements base = baseDocument.select(".products-i__link");

            List<String> datas = new ArrayList<>();

            for (Element element : base) {
                datas.clear();
                String carUrl = baseUrl + element.attr("href").substring(6);
                Document carDocument = Jsoup.connect(carUrl).get();
                Elements carSpecs = carDocument.select(".product-properties__i");
                Elements carPrices = carDocument.select(".product-price__i--bold");

                Car car = new Car();

                Optional<String> price = Optional.empty();
                for (Element p : carPrices) {
                    price = Optional.of(p.text());
                }
                for (Element e : carSpecs) {
                    String name = e.selectFirst(".product-properties__i-name").text();
                    String value = e.selectFirst(".product-properties__i-value").text();
                    datas.add(name + ": " + value);
                }
                car.setPrice(price.orElse(null));
                car.setCity(datas.stream().filter(x -> x.startsWith("Ş")).findFirst().map(s -> s.substring(7)).orElse(null));
                car.setModel(datas.stream().filter(x -> x.startsWith("Mo")).findFirst().map(s -> s.substring(7)).orElse(null));
                car.setBrand(datas.stream().filter(x -> x.startsWith("Ma")).findFirst().map(s -> s.substring(7)).orElse(null));
                car.setReleaseDate(datas.stream().filter(x -> x.startsWith("Bu")).findFirst().map(s -> s.substring(15)).orElse(null));
                car.setBan(datas.stream().filter(x -> x.startsWith("Ba")).findFirst().map(s -> s.substring(10)).orElse(null));
                car.setColor(datas.stream().filter(x -> x.startsWith("R")).findFirst().map(s -> s.substring(6)).orElse(null));
                car.setEngine(datas.stream().filter(x -> x.startsWith("Mü")).findFirst().map(s -> s.substring(10)).orElse(null));
                car.setRidingDistance(datas.stream().filter(x -> x.startsWith("Yü")).findFirst().map(s -> s.substring(7)).orElse(null));
                car.setTransmission(datas.stream().filter(x -> x.startsWith("S")).findFirst().map(s -> s.substring(15)).orElse(null));
                car.setDriveUnite(datas.stream().filter(x -> x.startsWith("Ö")).findFirst().map(s -> s.substring(9)).orElse(null));
                car.setIsNew(datas.stream().filter(x -> x.contains("Yeni")).findFirst().map(s -> s.substring(6)).orElse(null));
                car.setSeatNumber(datas.stream().filter(x -> x.contains("Yer")).findFirst().map(s -> s.substring(15)).orElse(null));
                car.setCondition(datas.stream().filter(x -> x.startsWith("V")).findFirst().map(s -> s.substring(11)).orElse(null));
                car.setRegion(datas.stream().filter(x -> x.startsWith("H")).findFirst().map(s -> s.substring(26)).orElse(null));
                cars.add(car);
            }
            System.out.println(datas);
        }
        System.out.println(cars);


        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet();
            HSSFRow dataNameRow = sheet.createRow(0);

            dataNameRow.createCell(0).setCellValue("QIYMET");
            dataNameRow.createCell(1).setCellValue("SEHER");
            dataNameRow.createCell(2).setCellValue("MARKA");
            dataNameRow.createCell(3).setCellValue("MODEL");
            dataNameRow.createCell(4).setCellValue("BURAXILIS ILI");
            dataNameRow.createCell(5).setCellValue("BAN NOVU");
            dataNameRow.createCell(6).setCellValue("RENG");
            dataNameRow.createCell(7).setCellValue("MUHERRIK");
            dataNameRow.createCell(8).setCellValue("YURUS");
            dataNameRow.createCell(9).setCellValue("SURETLER QUTUSU");
            dataNameRow.createCell(10).setCellValue("OTURUCU");
            dataNameRow.createCell(11).setCellValue("YENI");
            dataNameRow.createCell(12).setCellValue("YERLERIN SAYI");
            dataNameRow.createCell(13).setCellValue("VEZIYYETI");
            dataNameRow.createCell(14).setCellValue("HANSI BAZAR UCUN YIGILIB");

            int rownum = 1;
            for (Car car : cars) {
                HSSFRow valueRow = sheet.createRow(rownum);
                valueRow.createCell(0).setCellValue(car.getPrice());
                valueRow.createCell(1).setCellValue(car.getCity());
                valueRow.createCell(2).setCellValue(car.getBrand());
                valueRow.createCell(3).setCellValue(car.getModel());
                valueRow.createCell(4).setCellValue(car.getReleaseDate());
                valueRow.createCell(5).setCellValue(car.getBan());
                valueRow.createCell(6).setCellValue(car.getColor());
                valueRow.createCell(7).setCellValue(car.getEngine());
                valueRow.createCell(8).setCellValue(car.getRidingDistance());
                valueRow.createCell(9).setCellValue(car.getTransmission());
                valueRow.createCell(10).setCellValue(car.getDriveUnite());
                valueRow.createCell(11).setCellValue(car.getIsNew());
                valueRow.createCell(12).setCellValue(car.getSeatNumber());
                valueRow.createCell(13).setCellValue(car.getCondition());
                valueRow.createCell(14).setCellValue(car.getRegion());
                rownum++;
            }


            try (FileOutputStream outputStream = new FileOutputStream("src/main/resources/cars.xlxs")) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}