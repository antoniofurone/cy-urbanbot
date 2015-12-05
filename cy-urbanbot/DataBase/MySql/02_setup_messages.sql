delete from BSST_AME_APP_MESSAGE;

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WAIT_LOCK_SESSION','Attendi un attimo .....');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WAIT_LOCK_SESSION','Wait a moment .....');



insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'ERROR','Scusa. Si è verificato un errore');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'ERROR','Sorrry. Error is verified');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WELCOME_MENU','Benvenuto in Carovigno Smart City Bot (Beta version). Inviami uno dei seguenti comandi: /s segnalazioni;  /t narra ; /e eventi; /i itinerari; /m mappe;  /l change language.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WELCOME_MENU','Welcome at Carovigno Smart City Bot (Beta version). Send one of following commands: /s warnings; /t tell; /e events; /i itineraries; /m maps; /l cambia lingua.');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'COMMAND_NOT','Comando non riconosciuto...scusami.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'COMMAND_NOT','Command not recognized...sorry.');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'INVALID_SESSION','Stato invalido. Invia /start');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'INVALID_SESSION','Invalid status. Send /start');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_SHOW_OP','Seleziona un operazione: /s invia segnalazione; /v visualizza le tue segnalazione o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_SHOW_OP','Select operation: /s send warning; /v show your warning o /b to return back');



insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_TEXT','Scrivi la segnalazione o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_TEXT','Write warning o /b to return back');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_IMGLOC','Grazie. Ora invia una Location o un Immagine o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_IMGLOC','Thanks. Now send Location or Image or /b to return back');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_LOCOK','Grazie. Location acquisita.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_LOCOK','Thanks. Location acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_IMG','Ora invia un Immagine o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_IMG','Now send Image or /b to return back');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_IMGOK','Grazie. Immagine acquisita.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_IMGOK','Thanks. Image acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_LIST','Le tue segnalazioni sono le seguenti:');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_LIST','Your warnings are the following:');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_LIST_OP','Invia /v #segn per visualizzare; /d #segn per cancellare; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_LIST_OP','Send /v #segn to show; /d #segn to delete; /b to return back.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_INVALID_ID','#segn non valida !');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_INVALID_ID','invalid #segn !');
