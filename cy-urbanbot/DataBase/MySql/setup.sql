delete from BSST_APA_APP_PARAM;
delete from BSST_AME_APP_MESSAGE;


delete from BSST_APP_APP;
insert into BSST_APP_APP(APP_S_NAME,APP_S_DESC) values ('UrbanBot','UrbanBot @ Carovigno');

insert into BSST_APA_APP_PARAM(APP_N_APP_ID,APA_S_NAME,APA_S_VALUE,APA_C_TYPE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
'bot_url','https://api.telegram.org/bot130643009:AAGW77QBGQV4A7G494zA_w2DfzCR0zqULCM/','S');

insert into BSST_APA_APP_PARAM(APP_N_APP_ID,APA_S_NAME,APA_S_VALUE,APA_C_TYPE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
'bot_file_url','https://api.telegram.org/file/bot130643009:AAGW77QBGQV4A7G494zA_w2DfzCR0zqULCM/','S');

insert into BSST_APA_APP_PARAM(APP_N_APP_ID,APA_S_NAME,APA_S_VALUE,APA_C_TYPE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
'download_path','/home/antonio/cy-urbanbot/downloads/','S');


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,
'waitforlocksession',
'Attendi un attimo .....'
);
insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,
'waitforlocksession',
'Wait a moment .....'
);

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,
'error',
'Scusa. Si è verificato un errore durante l''operazione'
);
insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,
'error',
'Sorrry. Error during operation is verified'
);

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,
'welcomemenu',
'Benvenuto in Carovigno Smart City Bot (by @antoniofurone). Inviami uno dei seguenti comandi: /s segnalazioni; /a avvisi; /t narratore ; /e eventi; /i itinerari; /m mappe; /p locali; /l change language.'
);
insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,
'welcomemenu',
'Welcome at Carovigno Smart City Bot (by @antoniofurone). Send one of following commands: /s warnings; /a advice; /t storyteller; /e events; /i itineraries; /m maps; /p places; /l cambia lingua.'
);


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,
'commandnot',
'Comando non riconosciuto...scusami.'
);
insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,
'commandnot',
'Command not recognized...sorry.'
);


insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,
'invalidsession',
'Stato invalido. Invia /start'
);
insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,
'invalidsession',
'Invalid status. Send /start'
);

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,
'showwarn_op',
'Seleziona un operazione: /s invia segnalazione; /v visualizza le tue segnalazione o /b per tornare indietro'
);
insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,
'showwarn_op',
'Select operation: /s send warning; /v show your warning o /b to return back'
);



insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,
'sendwarn',
'Scrivi la segnalazione o /b per tornare indietro'
);
insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,
'sendwarn',
'Write warning o /b to return back'
);

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,
'sendwarn_imgorloc',
'Grazie. Ora invia una Location o un Immagine o /b per tornare indietro'
);
insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,
'sendwarn_imgorloc',
'Thanks. Now send Location or Image or /b to return back'
);

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,
'sendwarn_locok',
'Grazie. Location acquisita.'
);
insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,
'sendwarn_locok',
'Thanks. Location acquired.'
);

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,
'sendwarn_img',
'Ora invia un Immagine o /b per tornare indietro'
);
insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,
'sendwarn_img',
'Now send Image or /b to return back'
);

insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
1,
'sendwarn_imgok',
'Grazie. Immagine acquisita.'
);
insert into BSST_AME_APP_MESSAGE(APP_N_APP_ID,LAN_N_LANG_ID,AME_S_ID,AME_S_VALUE)
values (
(select APP_N_APP_ID from BSST_APP_APP where APP_S_NAME='UrbanBot'),
2,
'sendwarn_imgok',
'Thanks. Image acquired.'
);

