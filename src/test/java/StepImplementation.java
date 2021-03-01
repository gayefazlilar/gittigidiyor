import com.thoughtworks.gauge.Step;

public class StepImplementation extends BaseTest {

    @Step("Anasayfa acildi")
    public void ToDoPage() {
        driver.get(url);
        System.out.println(convertTurkishChar("AnaSayfa Açıldı"));
    }

    @Step("<saniye> saniye bekle")
    public void waitElement(int key) throws InterruptedException {
        Thread.sleep(key * 1000);
        System.out.println(key + " saniye beklendi");
    }


    @Step("<key> elementine tikla")
    public void clickElement(String key) {
        enterClick(key);
    }

    @Step("<key> elementine <text> yaz")
    public void sendkeys(String key, String text) {
        sendkeysElement(key, text);
    }

    @Step("Acildigindan Emin ol <text>")
    public void checkPage(String text) {//
        String title = driver.getTitle();
        assertControl(title, text);
        System.out.println("Sayfa Dogru");
    }

    @Step("<key> elementine gel bekle")
    public void hoverelement(String key) {
        hoverElement(key);
    }

    @Step("<key> elementte varmi <text> kontrol et")
    public void elementkontrol(String key, String text) {
        assertTextControl(key, text);
    }

    @Step("<key> elementine gel <urun> sayfaya tikla")
    public void pageList(String key, String urun) {
        int index = 55;
        System.out.println("=============");
        listButtonClick(index, urun, key);
        System.out.println("index e tiklandi  " + index);

    }

    @Step("<key> dogru sayfa mi")
    public void pageControl(String key) {
        if (pageControlBase(key) == true) {
            System.out.println("sayfa dogru");
        } else
            System.out.println("dogru sayfa degil");
    }

    @Step("<key> fiyat tut")
    public void urunFiyat(String key) {
        fiatKar(key);
        System.out.println("====" + fiyat + "=====");
    }

    @Step("<key> ile karsilastir")
    public void karsilastir(String key) {

        String sayfaFiyat = fiyat;
        fiatKar(key);
        String sepetFiyat = fiyat;
        if(sayfaFiyat.equals(sepetFiyat)){
            System.out.println("Fiyatlar eşit");
        }
        else
            System.out.println("Eşit Ddeğil");
    }
}