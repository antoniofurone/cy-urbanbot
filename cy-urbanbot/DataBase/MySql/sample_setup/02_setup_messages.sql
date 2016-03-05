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
1,'ERROR','Scusa. Si e'' verificato un errore');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'ERROR','Sorrry. Error is verified');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WELCOME_MENU','Benvenuto in Carovigno Bot (Beta version). Inviami uno dei seguenti comandi: /s segnalazioni;  /n storie ; /t siti turistici; /m mappe;  /l change language.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WELCOME_MENU','Welcome at Carovigno Bot (Beta version). Send one of following commands: /s warnings; /n stories; /t tourist sites; /m maps; /l cambia lingua.');


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
1,'SESSION_TIMEOUT','Session scaduta. Invia /start se vuoi ricominciare. Ciao !');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'SESSION_TIMEOUT','Session timeout. Invia /start to restart. Hi !');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_SHOW_OP','/n nuova segnalazione; /v visualizza le tue segnalazioni o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_SHOW_OP','/n new warning; /v show your warnings o /b to return back');


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
1,'WARN_MEDIALOC','Grazie. Ora invia una Location o una Foto o un Video o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_MEDIALOC','Thanks. Now send Location or Picture or Video or /b to return back');

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
1,'WARN_MEDIA','Ora invia una Foto o un Video /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_MEDIA','Now send Picture or Videro or /b to return back');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_IMGOK','Grazie. Foto acquisita.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_IMGOK','Thanks. Photo acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_VIDEOOK','Grazie. Video acquisito.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_VIDEOOK','Thanks. Video acquired.');



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
1,'WARN_LIST_OP','Invia /v# per visualizzare; /d# per cancellare; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_LIST_OP','Send /v# to show; /d# to delete; /b to return back.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_INVALID_ID','# non valida !');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_INVALID_ID','invalid # !');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_NO_WARN','Non hai nessuna segnalazione; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_NO_WARN','You don''t have warnings; /b to return back.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_DEL','Segnalazione cancellata');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_DEL','Warning deleted');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_SEL_CATEGORY','Seleziona la categoria o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_SEL_CATEGORY','Warning deleted or /b to return back');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_SHOW_OP','/n nuova storia; /v visualizza le tue storie o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_SHOW_OP','/n new story; /v show your stories o /b to return back');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_LOC','Invia una location o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_LOC','Send location or /b to return back');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_TEXT','Scrivi la storia o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_TEXT','Write story or /b to return back');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_TEXT_OK','Grazie. Storia acquisita.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_TEXT_OK','Thanks. Story acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_MEDIA','Invia una foto, un video, un messaggio vocale, un audio (.ogg, .mp3) o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_MEDIA','Send photo, video, audio message, audio (.ogg, .mp3) or /b to return back');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_VOICEOK','Grazie. Messaggio vocale acquisito.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_VOICEOK','Thanks. Vocal message acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_IMGOK','Grazie. Foto acquisita.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_IMGOK','Thanks. Photo acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_VIDEOOK','Grazie. Video acquisito.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_VIDEOOK','Thanks. Video acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_AUDIOOK','Grazie. Audio acquisito.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_AUDIOOK','Thanks. Audio acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_NO_TELL','Non hai nessuna storia; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_NO_TELL','You don''t have stories; /b to return back.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_LIST','Le tue storie sono le seguenti:');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_LIST','Your stories are the following:');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_LIST_OP','Invia /v# per visualizzare; /d# per cancellare; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_LIST_OP','Send /v# to show; /d# to delete; /b to return back.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_INVALID_ID','# non valida !');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TELL_INVALID_ID','invalid # !');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TELL_DEL','Storia cancellata');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TEL_DEL','Story deleted');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TOURIST_LOC','Seleziona o digita una location o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TOURIST_LOC','Select or write location or /b to return back');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TOURIST_NO_SITE','Nessun sito; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TOURIST_NO_SITE','There aren''t sites; /b to return back.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TOURIST_LIST','Ci sono i seguenti siti da visitare:');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TOURIST_LIST','There are the following sites:');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TOURIST_LIST_OP','Invia /v# per visualizzare; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TOURIST_LIST_OP','Send /v# to show; /b to return back.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TOURIST_INVALID_ID','# non valida !');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TOURIST_INVALID_ID','invalid # !');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'MAP_URLS','Seleziona uno dei seguenti link:\n\n<strong>Segnalazioni</strong>\n Google Maps: http://192.168.1.5:8080/cy-bss-ironhorse/html/maps/urbanbot/warnings.html \n Open Street Map: http://192.168.1.5:8080/cy-bss-ironhorse/html/maps/urbanbot/warningsOSM.html \n\n<strong>Storie</strong>\n Google Maps: http://192.168.1.5:8080/cy-bss-ironhorse/html/maps/urbanbot/stories.html \n Open Street Map: http://192.168.1.5:8080/cy-bss-ironhorse/html/maps/urbanbot/storiesOSM.html \n\n<strong>Siti Turistici</strong>\n Google Maps: http://192.168.1.5:8080/cy-bss-ironhorse/html/maps/urbanbot/touristSites.html \n Open Street Map: http://192.168.1.5:8080/cy-bss-ironhorse/html/maps/urbanbot/touristSitesOSM.html  ');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'MAP_URLS','Select one of following links:\n\n<strong>Warnings</strong>\n Google Maps: http://192.168.1.5:8080/cy-bss-ironhorse/html/maps/urbanbot/warnings.html \n Open Street Map: http://192.168.1.5:8080/cy-bss-ironhorse/html/maps/urbanbot/warningsOSM.html \n\n<strong>Stories</strong>\n Google Maps: http://192.168.1.5:8080/cy-bss-ironhorse/html/maps/urbanbot/stories.html \n Open Street Map: http://192.168.1.5:8080/cy-bss-ironhorse/html/maps/urbanbot/storiesOSM.html \n\n<strong>Tourist Sites</strong>\n Google Maps: http://192.168.1.5:8080/cy-bss-ironhorse/html/maps/urbanbot/touristSites.html \n Open Street Map: http://192.168.1.5:8080/cy-bss-ironhorse/html/maps/urbanbot/touristSitesOSM.html  ');
