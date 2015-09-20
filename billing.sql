--------------------------------------------------------
--  File created - Friday-August-28-2015   
--------------------------------------------------------
 <!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <html xmlns="http://www.w3.org/1999/xhtml">
  
    <head>
      <script type="text/javascript" src="https://www.google.com/jsapi"></script>
      <script type="text/javascript">
        google.load("visualization", "1", {packages:["geochart"]});
        google.setOnLoadCallback(drawRegionsMap);
		
	// Decoding table: https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2 	
 var reg_array=['US','BR','IN' ];
        function drawRegionsMap() {
 	
          var data = google.visualization.arrayToDataTable([
            ['Country', 'Popularity'],
            ['United States', 'United States: 300'],
            ['Brazil', 'Brazil: 400'],
            ['India', 'India: 500']
          ]);
  
          var options = {dataMode: 'regions'};
      function myClickHandler(){
          var selection = chart.getSelection();
          var codex = '';
          for (var i = 0; i < selection.length; i++) {
              var item = selection[i];
              var codex=item.row;
          }
		  if(codex!='')
		       drawRMap(codex);
	  }
          var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));
       google.visualization.events.addListener(chart, 'select', myClickHandler);
          chart.draw(data, options);
    }
     function drawRMap(codex) {
	
		var region=reg_array[codex];
		//alert (region);
        var data = google.visualization.arrayToDataTable([
          ['State', 'Popularity'],
 		  ['US-AL', '300'],
          ['US-MT', '300'],
		  ['IN-HP', 'Himachal Pradesh: 300'],
		  ['IN-DL', 'Delhi: 300'],
  		  ['IN-TN', 'Tamil Nadu : 300']

        ]);

        var options = { region: region ,resolution: 'provinces', domain: 'IN'};

        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

        chart.draw(data, options);

		
      }
      </script>
      
      
    </head>
    <body>
      <div id="regions_div" style="width: 900px; height: 500px; float:left"></div>
            <div style="width: auto; height: auto; float:right"><input type="button" onclick="drawRegionsMap()" value="Show World Map"  ></div>
    </body>
  
  </html>
-->

--------------------------------------------------------
--  DDL for Sequence SEQ_1
--------------------------------------------------------

   CREATE SEQUENCE  "HR"."SEQ_1"  MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 1001 CACHE 20 NOORDER  CYCLE ;
--------------------------------------------------------
--  DDL for Table TAX_MASTER
--------------------------------------------------------

  CREATE TABLE "HR"."TAX_MASTER" 
   (	"TAX_ID" NUMBER(10,0), 
	"PRODUCT_ID" NUMBER(8,0), 
	"STATE_CODE" NUMBER, 
	"TAX_RATE" FLOAT(126)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PAYMENTS_SUMMARY
--------------------------------------------------------

  CREATE TABLE "HR"."PAYMENTS_SUMMARY" 
   (	"ACCOUNT_NUMBER" VARCHAR2(20 BYTE), 
	"BILLING_DATE" DATE, 
	"BILLED_AMOUNT" VARCHAR2(20 BYTE), 
	"AMOUNT_RECEIVED" VARCHAR2(20 BYTE), 
	"PAYMENT_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PAYMENTS_OUTSTANDING_SUMMARY
--------------------------------------------------------

  CREATE TABLE "HR"."PAYMENTS_OUTSTANDING_SUMMARY" 
   (	"ACCOUNT_NUMBER" NUMBER(18,0), 
	"DATE" DATE, 
	"OUTSTANDING_AMOUNT" NUMBER(18,2)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table BILL_CYCLE_DETAILS
--------------------------------------------------------

  CREATE TABLE "HR"."BILL_CYCLE_DETAILS" 
   (	"ACCOUNT_NUM" NUMBER(18,0), 
	"BILL_INPUT" VARCHAR2(4000 BYTE), 
	"BILL_GENERATED" VARCHAR2(4000 BYTE), 
	"BILL_TOTAL_AMOUNT" NUMBER(18,2), 
	"DATE_TS" DATE, 
	"PREVIOUS_BILL_1" VARCHAR2(4000 BYTE), 
	"PREVIOUS_BILL_2" VARCHAR2(4000 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table BILL_CYCLE_SUMMARY
--------------------------------------------------------

  CREATE TABLE "HR"."BILL_CYCLE_SUMMARY" 
   (	"BILL_CYCLE_ID" VARCHAR2(20 BYTE), 
	"BILL_CYCLE_DATE" DATE, 
	"TOTAL_NUM_ACCNTS" NUMBER(18,0), 
	"PORTFOLIO" VARCHAR2(5 BYTE), 
	"STATUS_P_C_F" VARCHAR2(5 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into HR.TAX_MASTER
SET DEFINE OFF;
Insert into HR.TAX_MASTER (TAX_ID,PRODUCT_ID,STATE_CODE,TAX_RATE) values (10,10,1,0.5);
REM INSERTING into HR.PAYMENTS_SUMMARY
SET DEFINE OFF;
REM INSERTING into HR.PAYMENTS_OUTSTANDING_SUMMARY
SET DEFINE OFF;
REM INSERTING into HR.BILL_CYCLE_DETAILS
SET DEFINE OFF;
REM INSERTING into HR.BILL_CYCLE_SUMMARY
SET DEFINE OFF;
--------------------------------------------------------
--  Constraints for Table BILL_CYCLE_SUMMARY
--------------------------------------------------------

  ALTER TABLE "HR"."BILL_CYCLE_SUMMARY" MODIFY ("BILL_CYCLE_ID" NOT NULL ENABLE);

--------------------------------------------------------
--  DDL for PROCEDURES
--------------------------------------------------------


create or replace procedure BILL_DETAILS(P_ACCOUNT_NUM in varchar2,P_BILL_INPUT in varchar2,P_BILL_GENERATED in varchar2,P_BILL_TOTAL_AMOUNT in FLOAT,P_DATE_TS IN DATE,P_PREVIOUS_BILL_1 IN VARCHAR2,P_PREVIOUS_BILL_2 IN VARCHAR2)as

    begin
    insert into BILL_CYCLE_DETAILS values(P_ACCOUNT_NUM,P_BILL_INPUT,P_BILL_GENERATED,P_BILL_TOTAL_AMOUNT,P_DATE_TS,P_PREVIOUS_BILL_1,P_PREVIOUS_BILL_2);
    end;

create or replace procedure BILL_DETAILS_UPDATE(P_ACCOUNT_NUM in varchar2,P_BILL_INPUT in varchar2,P_BILL_TOTAL_AMOUNT in FLOAT,P_DATE_TS IN DATE)as

    begin
    update BILL_CYCLE_DETAILS set BILL_INPUT=P_BILL_INPUT, BILL_TOTAL_AMOUNT=P_BILL_TOTAL_AMOUNT, DATE_TS=P_DATE_TS where ACCOUNT_NUM=P_ACCOUNT_NUM;
    end;

create or replace procedure BILL_DETAILS_UPDATE1(P_ACCOUNT_NUM in varchar2,P_BILL_GENERATED in varchar2,P_PREVIOUS_BILL_1 IN VARCHAR2,P_PREVIOUS_BILL_2 IN VARCHAR2)as

    begin
    update BILL_CYCLE_DETAILS set BILL_GENERATED=P_BILL_GENERATED, PREVIOUS_BILL_1=P_PREVIOUS_BILL_1, PREVIOUS_BILL_2=P_PREVIOUS_BILL_2 where ACCOUNT_NUM=P_ACCOUNT_NUM;
    end;


