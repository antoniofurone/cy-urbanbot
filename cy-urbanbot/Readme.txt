Urbanbot is a framework to build telegram bot through which citizen can send warnings to city administration, tell story lived
in a particulare place and find tourist sites around them.

Urbanbot is based on others two open source projects: cy-bss-core (https://github.com/antoniofurone/cy-bss-core.git)
and cy-bss-ironhorse (https://github.com/antoniofurone/cy-bss-ironhorse.git).

Installation
------------ 
1) Download zip from https://github.com/antoniofurone/cy-urbanbot.git (or import project in Eclipse);
2) after cy-bss-core and cy-bss-ironhorse installation, from root folder of project (contains build.xml) run ant;
3) go in DataBase/MySql/sample_setup, customize and run 01_setup.sql script. In this script you must set the 
url for access at your bot (https://api.telegram.org/<bot_token>/), download path for files, latitude and longitude
of place, if mediation on media sent by user is necessary (app param media_mediation) and warning category;
4) run 02_setup_message.sql from DataBase/MySql/sample_setup. Also in this case, you can customize script changing the message 
sent to user from bot;
5) start application running in background urbanbot.sh (or .bat), for example: ./urbanbot.sh &


 
	