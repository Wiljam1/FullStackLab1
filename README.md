# FullStackLab1

Installera Node.js, Postman, MySQL Server + MySQL Workbench, npm, Java, IntelliJ, Visual Studio Code


Följande paket används via npm;

https://www.npmjs.com/package/bootstrap

https://www.npmjs.com/package/axios

https://www.npmjs.com/package/react-router-dom


Bra att installera React Developer Tools som tillägg i Webbläsaren.


MySQL server används som databas, behöver ha följande inloggning (eller ändra någonstans till den man har, för att få access till databasen);
username: root

password: wiljamniklas

Man behöver populera databasen själv, ska gå att göra med hjälp av frontend-programmet (forms som skickas via axios -> Spring -> MySQL)

Enklast att ha frontend öppet i Visual Studio Code och backend öppet i IntelliJ


STARTA FRONT END: "npm start" i terminal i mappen "patientjournal-front". Kanske behöver skriva "npm install" först.

STARTA BACK END: Starta som vanligt i IntelliJ.

STARTA MED DOCKER: "docker compose up" i backend och "docker compose up --build" i frontend.


Frontend körs på: http://localhost:3000/

Backend körs på: http://localhost:8080/


* Använd Postman för att testa backend (när man gör en ny mapping)
* https://getbootstrap.com/ för att söka efter färdiga frontend-komponenter (tables, navbar osv)
