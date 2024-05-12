## Yleisrakenne

datastructureproject-pakkauksen sisällä ohjelman rakenne on kolmeen osaan.

AntonBot-luokka vastaa siirron palauttamisesta, parhaan siirron valitsemisesta sekä shakkimatin havaitsemisesta.

AntonBoard-luokka vastaa laudan toiminnasta. Se muun muassa löytää siirrot, määrittää lailliset siirrot ja sisältää pelin arviointiin käytetyn heurestiikkafunktion. 

Move-luokka on apuluokka, joka helpottaa siirtojen perumista. 

## Saavutettu aikavaativuus

Minimax-funktion aikavaativuus on O(b^m) jossa b on sallittujen siirtojen määrä ja m on laskentasyvyys. Sallittuja siirtoja on pelin alussa 20, mutta pelin edetessä niitä voi olla jopa 218. Laskentasyvyydellä 5 aikavaativuus on siis välillä O(20^5), O(218^5).
Koska alpha-beta-karsinnan tapahtumisesta ei voida olla varmoja, se ei vaikuta pahimman tapauksen aikavaativuuteen. 

## Puutteet

Tämä shakkibotti hyväksy seuraavia siirtoja: ohestalyönti, patti, linnoitus, ylennys muuksi kuin kuningattareksi.
Se ei myöskään tunnista häviämistä siirtojen toistolla, 50 siirron säännöllä tai ajan loppumisella. 

## LLM-mallien käyttö
En käyttänyt projektissa LLM-malleja. 
