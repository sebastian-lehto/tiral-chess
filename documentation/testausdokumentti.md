# Testausdokumentti

## Kattavuusraportti

## Mitä on testattu

AntonBoard-luokan testauksessa varmistetaan, että luokan sisältämä lautaa edustava board-muuttuja on oikeassa tilassa, eli kaikki nappulat ovat oikeilla paikoillaan.

AntonBot-luokan testauksessa varmistetaan, että botti löytää omalla laskentasyvyydellään olevan shakkimatin. Tämä on tehty asettamalla lauta tilanteeseen, jossa on pakotettu shakkimatti botin laskentasyvyydellä, jonka jälkeen botilta pyydetään siirtoja, kunnes shakkimatin tulisi olla löydetty. Tämän jälkeen tarkistetaan onko peli botin mukaan päättynyt.
Botti testataan kolmella mate in three -tilanteella. Pelin toimintalogiikan mukaan peli päättyy ainoastaan oikealla voittoarvolla 10005 tai -10005. Testien läpäiseminen varmistaa, että botti on löytänyt shakkimatin pienimmällä mahdollisella määrällä siirtoja. 
Testeissä esiintyvissä tilanteissa botin tekemiin siirtoihin vastataan parhailla mahdollisilla siirroilla. 

Testauksen syötteenä on valmiina pelille annettu tila, koska tilanteeseen pääseminen yksittäisien siirtojen avulla olisi turhan työlästä. Testit voidaan toistaa ajamalla ne uudestaan tai pelaamalla bottia vastaan, kunnes päädytään mate in three -tilanteeseen. 
