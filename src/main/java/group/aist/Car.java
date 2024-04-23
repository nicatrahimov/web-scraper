package group.aist;

public class Car {
    private String price;
    private String city;
    private String brand;
    private String model;
    private String releaseDate;
    private String ban;
    private String color;
    private String engine;
    private String ridingDistance;
    private String transmission;
    private String driveUnite;
    private String isNew;
    private String seatNumber;
    private String owner;
    private String condition;
    private String region;


    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String  getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String motor) {
        this.engine = motor;
    }

    public String getRidingDistance() {
        return ridingDistance;
    }

    public void setRidingDistance(String ridingDistance) {
        this.ridingDistance = ridingDistance;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDriveUnite() {
        return driveUnite;
    }

    public void setDriveUnite(String driveUnite) {
        this.driveUnite = driveUnite;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", city='" + city + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", ban='" + ban + '\'' +
                ", color='" + color + '\'' +
                ", engine='" + engine + '\'' +
                ", ridingDistance='" + ridingDistance + '\'' +
                ", transmission='" + transmission + '\'' +
                ", driveUnite='" + driveUnite + '\'' +
                ", isNew='" + isNew + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", owner='" + owner + '\'' +
                ", condition='" + condition + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
