import java.util.Random;
import java.util.Scanner;

/**
 * @author zoroglu.resul
 */
public class LinkedList<K> {

    int uzunluk = 0;
    Dugum<K> basDugum;
    Dugum<K> sonDugum;
    private static Scanner okuyucu;
    public static void main(String[] args) {
    	LinkedList<Integer> liste = new LinkedList<>();
        okuyucu = new Scanner(System.in);
        System.out.print("Oluşturulacak Düğüm sayısı girin : ");
        Integer dugumSayisi = okuyucu.nextInt();
        System.out.print("\n");
        Random rastgele = new Random();
        liste.basaEkle(rastgele.nextInt(dugumSayisi*50));
        for (int i = 0; i < dugumSayisi-1; i++) {
            liste.sonaEkle(rastgele.nextInt(dugumSayisi*50));
        }
        liste.listele();
        System.out.println("\n");
        System.out.print("Yeni eklenecek olan Düğüm değerini giriniz : ");
        Integer yeniEklenecekDeger = okuyucu.nextInt();
        System.out.print("\n");
        System.out.print("Hangi Düğümden Sonra eklenecek : ");
        Integer hangiDugumdenSonra = okuyucu.nextInt();
        if (liste.dugumBul(hangiDugumdenSonra) == null) {
            throw new NullPointerException("Belirtilen Düğüm Bulunamadı");
        }
        liste.belirlenenYereEkle(yeniEklenecekDeger, hangiDugumdenSonra);
        System.out.print("\n");
        liste.listele();
    }

    public Dugum<K> dugumBul(K value) {
        Dugum<K> dugum = basDugum;
        if (dugum.sonrakiDugum == null) {
            if(dugum.item == value) {
                return dugum;
            }
        } else {
            while (dugum.sonrakiDugum != null) {
                if (dugum.item.equals(value)) {
                    return dugum;
                }
                dugum = dugum.sonrakiDugum;
            }
            if(dugum.item.equals(value)) {
                return dugum;
            }
        }
        return null;
    }

    public void belirlenenYereEkle(K eklenecekDeger, Integer nereyeEklenecek) {
        Dugum<K> suankiDugum = basDugum;
        for (int i = 0; i < uzunluk; i++) {
            while (!suankiDugum.item.equals(nereyeEklenecek) && suankiDugum.sonrakiDugum != null) {
                suankiDugum = suankiDugum.sonrakiDugum;
            }
        }
        Dugum<K> yeniEklenecekDugum = new Dugum<K>(eklenecekDeger);
        yeniEklenecekDugum.sonrakiDugum = suankiDugum.sonrakiDugum;
        yeniEklenecekDugum.oncekiDugum = suankiDugum;
        suankiDugum.sonrakiDugum = yeniEklenecekDugum;
        uzunluk++;
    }

    public void sonaEkle(K value) {
        Dugum<K> yeniDugum = new Dugum<K>(value);
        if (null == sonDugum) {
            yeniDugum.sonrakiDugum = null;
            yeniDugum.oncekiDugum = null;
            basDugum = yeniDugum;
            sonDugum = yeniDugum;
            uzunluk++;
        } else {
            sonDugum.sonrakiDugum = yeniDugum;
            yeniDugum.sonrakiDugum = null;
            yeniDugum.oncekiDugum = sonDugum;
            sonDugum = yeniDugum;
            uzunluk++;
        }
    }

    public void listele() {
        Dugum<K> dugum = basDugum;
        while (dugum != null) {
            System.out.print("[" + dugum.item + "]");
            dugum = dugum.sonrakiDugum;
            if (dugum != null) {
                try {
                    Thread.sleep(175);
                    System.out.print("-");
                    Thread.sleep(175);
                    System.out.print("-");
                    Thread.sleep(175);
                    System.out.print(">");
                    Thread.sleep(175);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void basaEkle(K value) {
        Dugum<K> yeniDugum = new Dugum<K>(value);
        if (null == basDugum) {
            yeniDugum.sonrakiDugum = null;
            yeniDugum.oncekiDugum = null;
            basDugum = yeniDugum;
            sonDugum = yeniDugum;
            uzunluk++;
        } else {
            yeniDugum.sonrakiDugum = basDugum;
            yeniDugum.oncekiDugum = null;
            basDugum.oncekiDugum = yeniDugum;
            basDugum = yeniDugum;
            uzunluk++;
        }
    }

    public class Dugum<E> {
        E item;
        Dugum<E> sonrakiDugum;
        Dugum<E> oncekiDugum;

        public Dugum(E item) {
            this.item = item;
        }
    }
}