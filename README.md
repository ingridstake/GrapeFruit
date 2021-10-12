# GrapeFruit

Trello: https://trello.com/b/Gn34hYMN/hitta-den-försvunna-kossan

UML: https://drive.google.com/file/d/1iG93vHK2u4bNr7c1V3XxUbskmbH0dbxU/view?usp=sharing

SDD: https://www.overleaf.com/read/hvpfgfgqmqwp

RAD: https://www.overleaf.com/read/gfccrqkyfhnj


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

