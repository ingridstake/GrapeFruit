# GrapeFruit

Trello: https://trello.com/b/Gn34hYMN/hitta-den-försvunna-kossan

UML: https://drive.google.com/file/d/1iG93vHK2u4bNr7c1V3XxUbskmbH0dbxU/view?usp=sharing

SDD: https://www.overleaf.com/read/hvpfgfgqmqwp

RAD: https://www.overleaf.com/read/gfccrqkyfhnj

Spelregler:
Börja med att välja antal spelare som vill spela och tryck sedan på starta spelet.
Varje spelare får en egen färg som gäller hela spelrundan. 
Turas om att slå tärningen för att förlytta sig på spelplanen. 
Om man hamnar på en markör behöver man välja mellan att slå tärningen för att öppna eller att betala 1000kr. Alla spelarnas belopp synd på vardera spelkort.
Beroende på vad som ligger under markören händer olika saker: Blank - inget, komocka - rånad, gris - 500kr, häst - 900kr, kobjällra - chans att vinna, ko - chans att vinna.
För att vinna : Det gäller att man hittar kossan som rymt och gömt sig någonstans på spelplanen och sedan ta sig tillbaka till startpositionen igen. Men om kossan är funnen och en annan spelare hittar en kobjällra kan denna spelare ta sig till startpositionen för att lura dit kon och vinna spelet. Försten till startpositionen vinner! 

## Krav för .json fil
För att en JSON fil skall kunna läsas in som spelets spelbräde måste den vara uppbyggd på följande sätt:

    - JSONObject som har
      - en JSONArray *PositionList* vilken består av 
         - positionID : int
          - positionType : String 
         - X : int
         - Y : int

      - en JSONArray *Neighbours* vilken består av 
         - id : int
         - neighbours : int Array[]
    
### Exempel

{
  "PositionList": [
    {
        "positionID": 0,
        "positionType": "NormalPosition",
        "X": 1,
        "Y": 1
    }
  ],
    "Neighbours": 
    {
      "id": 0,
      "neighbours": [...]
    }
  ]
}

