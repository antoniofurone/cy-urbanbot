delete from BSST_APA_APP_PARAM;


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

