# Az alábbi repository a vizsgaremekemet tartalmazza,

a program idősek otthonának íródott, amely segítségével tárolni lehet a bentlakók adatait, mint név és születési dátum,
lakcím. Ezen kívül lehetőség van rögzíteni a bentlakók pénzügyi kiadásait, mindenkihez tartozik egy pénzügyek funkció,
ahol tárolni lehet a kiadásokat, bevételeket, továbbá ezeket a pénzmozgásokat kommentelni is kehet (pl: nyugdíj).

## Technológiák

* Klasszikus háromrétegű alkalmazás
* Java Spring backenddel
* REST webszolgáltatásokkal.
* Spring Data JPA adatbáziskezelés
* MariaDB adatbázissal
* Flyway migrációs adatbázis verziókezelés
* Swagger
* Integrációs tesztek RestTemplate használatával
* Unit tesztek Mockolt service rétegekkel

## Entitások és kapcsolataik

* Bentlakó (Elder)
* Lakcím (Address)
* Pénzügy (Finance)
* Számla (Invoice)


![img_1.png](img_1.png)

## Végpontok:

###### Az alkalmazás elindítása után a részletes dokumentáció elérhetó a localhost:8080/swagger-ui.html címen

* ElderController végpontjai:
    * /api/elders
        * Ezen a végponton megvalósítható egy kérés, ami listázza a idősotthon lakóit, vagy
        * egy PostMapping kéréssel felvehető egy új bentlakó.
    * /api/elders/{id}
        * Get kéréssel lekérdezhetó az adott id-jú bentlakó.
    * /api/elders/{id}/address
        * Frissíteni lehet az adott id-jú bentlakó lakcímét.
    * /api/elders/{id}/delete
      * törölni lehet az adott id-jú bentlakót, töröl minden hozzá tartozó adatot (CascadeType.REMOVE)

* FinanceController végpontjai:
  * /api/finances
    * Frissíteni lehet egy bentlakó pénzügyeit.
  * /api/finances/elder/{id}
    * A bentlakó Id-ja alapján le lehet kérdezni a hozzá tartozó pénzügyeket.
  * /api/finances/elder/{id}/invoice
    * Be lehet küldeni egy új számlát az adott id-jú bentlakónak.
  
### További tervek

* Egészségügyi események rögzítéséhez tartozó funkció kialakítása
    * Orvosi vizsgálatok mentése.
        * Betegségek mentése n-m kapcsolattal.
    * N-m kapcsolat kialakítása a gyógyszerek és az idősek között.

* Látogatások rögzítése.


