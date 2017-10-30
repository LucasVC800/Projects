libname mydata 'C:\Users\Lucas\Desktop\School\Senior Project\Data';
libname Master 'C:\Users\Lucas\Desktop\School\Senior Project\Master';
Proc sort data=mydata.con09;
by CON09_ID;
run;
Proc sort data=mydata.con11;
by CON11_ID;
run;
Proc sort data=fam2009er;
by fam2009er_ID;
run;
Proc sort data=fam2011er;
by fam2011er_ID;
run;
Proc sort data=mydata.ind2013er;
by ind2013er_ID;
run;
DATA mydata.Merged11;
merge mydata.con11 mydata.fam2011er;
run;
Data mydata.Merged09;
merge mydata.con09 mydata.fam2009er;
run;
proc sort data=mydata.merged09;
by ER42009;
run;
proc sort data=mydata.merged11;
by ER47309;
run;
data mydata.IND2011;
set mydata.ind2013er;
IND2011 = .;
IF (ER34103=10) THEN Relevant11=1;
if (ER34103^=10) THEN Relevant11=0;
run;
data mydata.IND2009;
set mydata.ind2013er;
IND2009 = .;
IF (ER34003=10) THEN Relevant09=1;
if (ER34003^=10) THEN Relevant09=0;
run;
data mydata.ind2011;
set mydata.ind2011;
RENAME ER34101=ID11;
Run;
data mydata.merged11;
set mydata.merged11;
RENAME CON11_ID=ID11;
run;
proc sort data=mydata.ind2011;
by ID11;
run;
proc sort data=mydata.merged11;
by ID11;
run;
DATA mydata.ind2011relevant;
set mydata.ind2011;
if ID11=0 THEN delete;
IF ER34102^=1 THEN delete;
run;
DATA mydata.master11; 
  MERGE mydata.ind2011relevant mydata.merged11; 
  BY ID11; 
RUN;
data mydata.ind2009;
set mydata.ind2009;
RENAME ER34001=ID09;
Run;
data mydata.merged09;
set mydata.merged09;
RENAME CON09_ID=ID09;
run;
DATA mydata.ind2009relevant;
set mydata.ind2009;
if ID09=0 THEN delete;
IF ER34002^=1 THEN delete;
run;
proc sort data=mydata.ind2009relevant;
by ID09;
run;
proc sort data=mydata.merged09;
by ID09;
run;
DATA mydata.master09; 
  MERGE mydata.ind2009relevant mydata.merged09; 
  BY ID09; 
RUN;
data mydata.Arthritis09;
set mydata.Master09;
Arthritis09 = .;
IF (ER42018=1) THEN Male=1;
if (ER42018^=1) THEN Male=0;

if (ER46543=1) THEN White=1;
IF (ER46543^=1) THEN White=0;

if (ER46592=0) THEN Religious=0;
if (ER46592^=0) THEN Religious=1;

if (ER34022=0) THEN Insured=0;
if (ER34022^=0) THEN insured=1;

if (ER34007^=0) THEN Married=0;
if (ER34007=0) THEN Married=1;

if (ER44213^=7) THEN Limited=1;
if (ER44213=7) Then Limited=0;

If (ER44210^=1) THEN DELETE;
run;
data mydata.Arthritis11;
set mydata.Master11;
Arthritis11 = .;
IF (ER47318=1) THEN Male=1;
if (ER47318^=1) THEN Male=0;

if (ER51904=1) THEN White=1;
IF (ER51904^=1) THEN White=0;

if (ER51953=0) THEN Religious=0;
if (ER51953^=0) THEN Religious=1;

if (ER34121=0) THEN Insured=0;
if (ER34121^=0) THEN insured=1;

if (ER34107^=0) THEN Married=0;
if (ER34107=0) THEN Married=1;

if (ER49541^=7) THEN Limited=1;
if (ER49541=7) Then Limited=0;

If (ER49538^=1) THEN DELETE;
run;
data master.RetiredArthritis11;
set mydata.Arthritis11;
RetiredArthritis11 = .;
if (ER34104<65)or (ER34104=0) THEN delete;
if (ER34119=98) or (ER34119=99) THEN delete;
if (ER49629=98) or (ER49629=99) THEN delete;
if (ER49620=9) THEN delete;
if (ER49631=0) THEN delete;
if (ER49494=8) or (ER44175=9) THEN delete;
if (ER47624=998) THEN delete;

if (ER49620=1) THEN Smoke=1;
if (ER49620^=1) THEN Smoke=0;

Exercise = ER49616*ER49617;

if (PRESCR11>2527) THEN delete;
run;
data master.PreRetiredArthritis11;
set mydata.Arthritis11;
PreRetiredArthritis11 = .;
if(ER34104>=65)or(ER34104<=45)or (ER34104=0) THEN delete;
if (ER34104>=65) or (ER34104=0) THEN delete;
if (ER34119=98) or (ER34119=99) THEN delete;
if (ER49629=98) or (ER49629=99) THEN delete;
if (ER49620=9) THEN delete;
if (ER49631=0) THEN delete;
if (ER49494=8) or (ER44175=9) THEN delete;
if (ER47624=998) THEN delete;

if (ER49620=1) THEN Smoke=1;
if (ER49620^=1) THEN Smoke=0;

Exercise = ER49616*ER49617;

if (PRESCR11>2035.83) THEN delete;
run;

data Master.RetiredArthritis09;
set mydata.Arthritis09;
RetiredArthritis09 = .;
if (ER34004<65) or (ER34004=0) THEN delete;
if (ER34020=98) or (ER34020=99) THEN delete;
if (ER44291=98) or (ER44291=99) THEN delete;
if (ER44282=9) THEN delete;
if (ER44293=0) THEN delete;
if (ER44175=8) or (ER44175=9) THEN delete;
if (ER42311=998) THEN delete;

if (ER44282=1) THEN Smoke=1;
if (ER44282^=1) THEN Smoke=0;

Exercise = ER44278*ER44279;

if (PRESCR09>2688) THEN delete;
run;
data Master.PreRetiredArthritis09;
set mydata.Arthritis09;
RetiredArthritis09 = .;
if (ER34004>=65) or (ER34004=0)or (ER34004<=45) THEN delete;
if (ER34020=98) or (ER34020=99) THEN delete;
if (ER44291=98) or (ER44291=99) THEN delete;
if (ER44282=9) THEN delete;
if (ER44293=0) THEN delete;
if (ER44175=8) or (ER44175=9) THEN delete;
if (ER42311=998) THEN delete;

if (ER44282=1) THEN Smoke=1;
if (ER44282^=1) THEN Smoke=0;

Exercise = ER44278*ER44279;
if (PRESCR09>2400) THEN delete;
run;
proc reg data=master.retiredarthritis09;
model PRESCR09 = Male Married ER34020 White religious insured ER46935 ER44175 ER42311 Smoke limited exercise ER34004 ER44293;
run;
proc reg data=master.preretiredarthritis09;
model PRESCR09 = Male Married ER34020 White religious insured ER46935 ER44175 ER42311 Smoke limited exercise ER34004 ER44293;
run;
proc reg data=master.retiredarthritis11;
model PRESCR11 = Male White Religious Insured Married ER52343 ER34104 ER34119 ER49631 ER47624 smoke limited exercise ER49494;
run;
proc reg data=master.preretiredarthritis11;
model PRESCR11 = Male White Religious Insured Married ER52343 ER34104 ER34119 ER49631 ER47624 smoke limited exercise ER49494;
run;
proc means data=master.retiredarthritis09;
var PRESCR09 Male Married ER34020 White religious insured ER46935 ER44175 ER42311 Smoke limited exercise ER34004 ER44293;
run;
proc means data=master.preretiredarthritis09;
var PRESCR09 Male Married ER34020 White religious insured ER46935 ER44175 ER42311 Smoke limited exercise ER34004 ER44293;
run;
proc means data=master.retiredarthritis11;
var PRESCR11 Male White Religious Insured Married ER52343 ER34104 ER34119 ER49631 ER47624 smoke limited exercise ER49494;
run;
proc means data=master.preretiredarthritis11;
var PRESCR11 Male White Religious Insured Married ER52343 ER34104 ER34119 ER49631 ER47624 smoke limited exercise ER49494;
run;
ODS SELECT ExtremeObs;
PROC UNIVARIATE DATA=master.retiredarthritis09 NEXTROBS=10;
 VAR PRESCR09;
RUN; 
PROC UNIVARIATE DATA=master.preretiredarthritis09 NEXTROBS=10;
 VAR PRESCR09;
RUN; 
PROC UNIVARIATE DATA=master.retiredarthritis11 NEXTROBS=10;
 VAR PRESCR11;
RUN; 
PROC UNIVARIATE DATA=master.Preretiredarthritis11 NEXTROBS=10;
 VAR PRESCR11;
RUN; 
