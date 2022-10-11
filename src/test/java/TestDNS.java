import org.junit.Test;

public class TestDNS extends BaseTests {

//"Игровая консоль PlayStation 5 [825 ГБ SSD, геймпад - 1 шт, Bluetooth, Wi-Fi, белый]"
    @Test
    public void testDNS() {
        pageManager.getStartPage()
                .selectCity()
                .searching("PlayStation 5")
                .selectProduct("Игровая консоль PlayStation 5 [825")
                .setProdParam()
                .selectWarranty("+ 24 мес.")
                .buyProduct()
                .searching("Far Cry 6 для Playstation 5")
                .setProdParam()
                .buyProduct()
                .checkBasket()
                .selectBasket()
                .checkWarranty("Игровая консоль PlayStation 5")
                .checkPrice()
                .deleteProduct("Игра Far Cry 6 (PS5)")
                .checkPrice()
                .addQuantityProduct("Игровая консоль PlayStation 5", "3")
                .restoreProduct();
    }
}
