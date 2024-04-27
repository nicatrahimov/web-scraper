package group.aist;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    private static ArrayList<Car> cars=new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String baseUrl = "https://turbo.az/autos";
        Document baseDocument = Jsoup.connect(baseUrl).get();
        Elements select = baseDocument.select(".products-i__link");

        List<String>datas = new ArrayList<>();

        for (Element element : select) {
            datas.clear();
            String carUrl = baseUrl + element.attr("href").substring(6);
            Document carDocument = Jsoup.connect(carUrl).get();
            Elements carSpecs = carDocument.select(".product-properties__i");
            Elements carPrices = carDocument.select(".product-price__i--bold");

            Car car = new Car();

            Optional<String> price = Optional.empty();
            for(Element p : carPrices){
                price = Optional.of(p.text());
            }
            for (Element e : carSpecs) {
                String name = e.selectFirst(".product-properties__i-name").text();
                String value = e.selectFirst(".product-properties__i-value").text();
                datas.add(name+": "+value);
            }
            car.setPrice(price.orElse(null));
            car.setCity(datas.stream().filter(x->x.startsWith("Ş")).findFirst().map(s->s.substring(7)).orElse(null));
            car.setModel(datas.stream().filter(x-> x.startsWith("Mo")).findFirst().map(s->s.substring(7)).orElse(null));
            car.setBrand(datas.stream().filter(x->x.startsWith("Ma")).findFirst().map(s->s.substring(7)).orElse(null));
            car.setReleaseDate(datas.stream().filter(x->x.startsWith("Bu")).findFirst().map(s->s.substring(15)).orElse(null));
            car.setBan(datas.stream().filter(x->x.startsWith("Ba")).findFirst().map(s->s.substring(10)).orElse(null));
            car.setColor(datas.stream().filter(x->x.startsWith("R")).findFirst().map(s->s.substring(6)).orElse(null));
            car.setEngine(datas.stream().filter(x->x.startsWith("Mü")).findFirst().map(s->s.substring(10)).orElse(null));
            car.setRidingDistance(datas.stream().filter(x->x.startsWith("Yü")).findFirst().map(s->s.substring(7)).orElse(null));
            car.setTransmission(datas.stream().filter(x->x.startsWith("S")).findFirst().map(s->s.substring(15)).orElse(null));
            car.setDriveUnite(datas.stream().filter(x->x.startsWith("Ö")).findFirst().map(s->s.substring(9)).orElse(null));
            car.setIsNew(datas.stream().filter(x->x.contains("Yeni")).findFirst().map(s->s.substring(6)).orElse(null));
            car.setSeatNumber(datas.stream().filter(x->x.contains("Yer")).findFirst().map(s->s.substring(15)).orElse(null));
            car.setCondition(datas.stream().filter(x->x.startsWith("V")).findFirst().map(s->s.substring(11)).orElse(null));
            car.setRegion(datas.stream().filter(x->x.startsWith("H")).findFirst().map(s->s.substring(26)).orElse(null));
            cars.add(car);
            }
        System.out.println(datas);
        System.out.println(cars);
        }
    }
