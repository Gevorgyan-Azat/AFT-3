import org.junit.Test;

public class TestDNS extends BaseTests {

    @Test
    public void testDNS() {
        pageManager.getStartPage()
                .selectCity()
                .searching("Playstation 5")
                .selectProduct("Игровая консоль PlayStation 5 [825 ГБ SSD, геймпад - 1 шт, Bluetooth, Wi-Fi, белый]")
                .setPS5Param()
                .selectWarranty("+ 24 мес.")
                .buyProduct()
                .searching("Far Cry 6 для Playstation 5")
                .setFC6Param()
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
