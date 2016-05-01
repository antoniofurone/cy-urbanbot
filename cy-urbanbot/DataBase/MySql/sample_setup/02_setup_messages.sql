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
2,'SESSION_TIMEOUT','Session timeout. Send /start to restart. Hi !');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_SHOW_OP','/n nuova segnalazione; /r ricerca; /v visualizza le tue segnalazioni o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_SHOW_OP','/n new warning; /r search; /v show your warnings o /b to return back');




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
1,'WARN_GET_SEARCH','Inserisci il testo di ricerca (! per vedere tutto) o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_GET_SEARCH','Insert text search (! for all) o /b to return back');


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
1,'WARN_LIST_OP','Invia /v# per visualizzare; /d# per cancellare; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_LIST_OP','Send /v# to show; /d# to delete; /b to return back.');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_LIST_OP_N','Invia /v# per visualizzare; /d# per cancellare; /s successive; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_LIST_OP_N','Send /v# to show; /d# to delete; /s next; /b to return back.');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_LIST_OP_P','Invia /v# per visualizzare; /d# per cancellare; /p precedenti; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_LIST_OP_P','Send /v# to show; /d# to delete; /p previous; /b to return back.');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'WARN_LIST_OP_NP','Invia /v# per visualizzare; /d# per cancellare; /s successive; /p precedenti; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_LIST_OP_NP','Send /v# to show; /d# to delete; /s next; /p previous; /b to return back.');

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
1,'WARN_NO_WARN','Nessuna segnalazione; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'WARN_NO_WARN','No warnings; /b to return back.');

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
2,'WARN_SEL_CATEGORY','Select category or /b to return back');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_SHOW_OP','/n nuova storia; /r ricerca; /v visualizza le tue storie o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_SHOW_OP','/n new story; /r search; /v show your stories o /b to return back');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_LOC','Invia una location o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_LOC','Send location or /b to return back');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_TEXT','Scrivi la storia o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_TEXT','Write story or /b to return back');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_TEXT_OK','Grazie. Storia acquisita.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_TEXT_OK','Thanks. Story acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_GET_SEARCH','Inserisci il testo di ricerca (! per vedere tutto) o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_GET_SEARCH','Insert text search (! for all) o /b to return back');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_MEDIA','Invia una foto, un video, un messaggio vocale, un audio (.ogg, .mp3) o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_MEDIA','Send photo, video, audio message, audio (.ogg, .mp3) or /b to return back');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_VOICEOK','Grazie. Messaggio vocale acquisito.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_VOICEOK','Thanks. Vocal message acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_IMGOK','Grazie. Foto acquisita.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_IMGOK','Thanks. Photo acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_VIDEOOK','Grazie. Video acquisito.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_VIDEOOK','Thanks. Video acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_AUDIOOK','Grazie. Audio acquisito.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_AUDIOOK','Thanks. Audio acquired.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_NO_STORY','Nessuna storia; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_NO_STORY','No stories; /b to return back.');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_LIST_OP','Invia /v# per visualizzare; /d# per cancellare; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_LIST_OP','Send /v# to show; /d# to delete; /b to return back.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_LIST_OP_N','Invia /v# per visualizzare; /d# per cancellare; /s successive; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_LIST_OP_N','Send /v# to show; /d# to delete; /s next; /b to return back.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_LIST_OP_P','Invia /v# per visualizzare; /d# per cancellare; /p precedenti; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_LIST_OP_P','Send /v# to show; /d# to delete; /p previous; /b to return back.');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_LIST_OP_NP','Invia /v# per visualizzare; /d# per cancellare; /s successive; /p precedenti; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_LIST_OP_NP','Send /v# to show; /d# to delete; /s next; /p previous; /b to return back.');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_INVALID_ID','# non valida !');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_INVALID_ID','invalid # !');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'STORY_DEL','Storia cancellata');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'STORY_DEL','Story deleted');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TOURIST_LOC','Digita (es. Via Piave, Carovigno) o invia la tua posizione o /b per tornare indietro');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TOURIST_LOC','Write (e.g. Via Piave, Carovigno) or send your position /b to return back');

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
1,'TOURIST_LIST_OP','Invia /v# per visualizzare; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TOURIST_LIST_OP','Send /v# to show; /b to return back.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TOURIST_LIST_OP_N','Invia /v# per visualizzare; /s successive; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TOURIST_LIST_OP_N','Send /v# to show; /s next; /b to return back.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TOURIST_LIST_OP_P','Invia /v# per visualizzare; /p precedenti; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TOURIST_LIST_OP_P','Send /v# to show; /p previous; /b to return back.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,'TOURIST_LIST_OP_NP','Invia /v# per visualizzare; /s successive; /p precedenti; /b per tornare indietro.');

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,'TOURIST_LIST_OP_NP','Send /v# to show; /s next; /p previous; /b to return back.');

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
