Business Need: Als Anwender kann ich den Kontenplan ändern, d.h neue Konten hinzufügen.

  Scenario: Ein Anwender kann Konten hinzufügen
    Given ich habe den Kontenplan geöffnet
    When  ich ein neues Konto anlege
    Then  wurde das Konto dem Kontenplan hinzugefügt