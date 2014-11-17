# language: de
Funktionalität: Als Anwender kann ich den Kontenplan ändern und
  meinen Bedürfnissen anpassen.

  Szenario: Ein Anwender kann Konten hinzufügen
    Angenommen ich habe den Kontenplan geöffnet
    Wenn  ich ein neues Aufwands-Konto "4001" "Lebensmittel" anlege
    Dann  wird das Konto "4001" "Lebensmittel" im Kontenplan angezeigt

  @Current
  Szenario: Ein Anwender kann Konten umbenennen
    Angenommen das Konto "4101" "Miete" wurde im Kontenplan angelegt
    Wenn ich das Konto "4101" in "Lebensmittel" umbenenne
    Dann wird das Konto "4101" "Lebensmittel" im Kontenplan angezeigt