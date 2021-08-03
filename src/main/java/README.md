# Az alábbi repository a vizsgaremekemet tartalmazza,

amely egy klasszikus háromrétegű alkalmazás, MariaDB adatbázissal, Java Spring backenddel, REST webszolgáltatásokkal.
Egy idősek otthonát kezelő program egy kis szeletét próbálom megvalósítani. Az alap entitás az idős bentlakó (Elder),
akinek van neve, címe, születési ideje és pénzügyei. Az Elder a lakcímével 1-1 kapcsolatban van, 1 Elderhez csak egy
cím, és egy címhez csak 1 Elder tartozhat. Minden Eldernek vannak pénzügyei (Finance). A pénzügyek létrehozásánál meg
kell adni hogy melyik Elderhez tartozik és hogy mennyi a nyitő egyenleg. Minden pénzügyhöz csak 1 Elder tartozik, tehát
itt egy újabb 1-1 kapcsolat van. A pénzügyeknek két atribútuma van, az egyenleg - ami fedezi a költségeket, ebből lehet
gyógyszert extra élelmet stb.. venni - és a számlák, amik ezen egyenleget változtatják. A számlákat listában tároljuk,
amikor egy számla bekerül, akkor csökkenti a pénzügyeknél meglévő egyenleget (tranzakció kezelés, BigInteger használata)
.

### Végpontok:

* idős hozzáadása,
* lakcím beállítása/frissítése,
* pénzügyek létrehozása (1 időshöz egyszer lehet létrehozni),
* számla hozzáadása,
* idős törlése.

A localhost:8080/swagger-ui.html elérhető a program dokumentációja, és a végpontok tesztelésére is lehetőség van.
