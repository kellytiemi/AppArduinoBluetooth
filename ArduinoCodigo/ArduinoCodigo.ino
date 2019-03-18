#include <SoftwareSerial.h>
SoftwareSerial bluetooth(10,11);

#define led1 2
#define led2 3
#define led3 4
#define led4 5
#define led5 6
#define led6 7
#define led7 8
#define led8 9

String comando;
int i;
int piscar, repetir, repetir2;
String comandoAnterior;

void ListenBluetooth()
{
char caracter = bluetooth.read();
comando += caracter;
delay(10);
}


boolean TryStop()
{
ListenAll();
if(comando = "stop")
{
return true;
}
else{return false;}
}


void ListenAll(){
if (bluetooth.available())
{
while(bluetooth.available())
{
ListenBluetooth();
}
}
}

void setup() {
  
Serial.begin(9600);
bluetooth.begin(9600);
piscar = 0;
pinMode(led1, OUTPUT);
pinMode(led2, OUTPUT);
pinMode(led3, OUTPUT);
pinMode(led4, OUTPUT);
pinMode(led5, OUTPUT);
pinMode(led6, OUTPUT);
pinMode(led7, OUTPUT);
pinMode(led8, OUTPUT);

}

void loop() {
  
comando = "";

if (bluetooth.available())
{
while(bluetooth.available())
{
ListenBluetooth();
}


fim:
if(comando.indexOf("alarme")>=0)
{
  piscar = 1;
}

if(comando.indexOf("stop")>=0)
{
  piscar = 0;
}

while(piscar > 0)
{
digitalWrite(led1,HIGH);
digitalWrite(led2,HIGH); 
digitalWrite(led3,HIGH); 
digitalWrite(led4,HIGH); 
digitalWrite(led5,HIGH); 
digitalWrite(led6,HIGH); 
digitalWrite(led7,HIGH); 
digitalWrite(led8,HIGH);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
delay(500);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
digitalWrite(led1,LOW);
digitalWrite(led2,LOW); 
digitalWrite(led3,LOW); 
digitalWrite(led4,LOW); 
digitalWrite(led5,LOW); 
digitalWrite(led6,LOW); 
digitalWrite(led7,LOW); 
digitalWrite(led8,LOW);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
delay(500);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
}



if(comando.indexOf("apagar")>=0)
{
digitalWrite(led1,LOW);
digitalWrite(led2,LOW); 
digitalWrite(led3,LOW); 
digitalWrite(led4,LOW); 
digitalWrite(led5,LOW); 
digitalWrite(led6,LOW); 
digitalWrite(led7,LOW); 
digitalWrite(led8,LOW); 
}



if(comando.indexOf("sequencial1")>=0)
{
  repetir = 1;
}

if(comando.indexOf("stop")>=0)
{
  repetir = 0;
}

while(repetir > 0)
{

digitalWrite(led1,HIGH);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
delay(1000);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
digitalWrite(led1,LOW); 

digitalWrite(led2,HIGH);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
delay(1000);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
digitalWrite(led2,LOW);

digitalWrite(led3,HIGH);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
delay(1000); 
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
digitalWrite(led3,LOW);

digitalWrite(led4,HIGH);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
delay(1000);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
digitalWrite(led4,LOW); 

digitalWrite(led5,HIGH);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
delay(1000);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
digitalWrite(led5,LOW);

digitalWrite(led6,HIGH);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
delay(1000);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
digitalWrite(led6,LOW); 

digitalWrite(led7,HIGH);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
delay(1000);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
digitalWrite(led7,LOW); 

digitalWrite(led8,HIGH);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
delay(1000);
if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
digitalWrite(led8,LOW); 

}


  


if(comando.indexOf("sequencial2")>=0)
{
  repetir2 = 1;
}

if(comando.indexOf("stop")>=0)
{
  repetir2 = 0;
}

while(repetir2 > 0)
{

  digitalWrite(led1,HIGH);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  delay(1000);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  digitalWrite(led2,HIGH);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  delay(1000);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  digitalWrite(led3,HIGH);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  delay(1000);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  digitalWrite(led4,HIGH);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  delay(1000);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  digitalWrite(led5,HIGH);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  delay(1000);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  digitalWrite(led6,HIGH);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  delay(1000);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  digitalWrite(led7,HIGH);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  delay(1000);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
  digitalWrite(led8,HIGH);
  
  delay(1000);
  if (bluetooth.available()){ if(TryStop() == true){goto fim;}}
digitalWrite(led1,LOW);
digitalWrite(led2,LOW); 
digitalWrite(led3,LOW); 
digitalWrite(led4,LOW); 
digitalWrite(led5,LOW); 
digitalWrite(led6,LOW); 
digitalWrite(led7,LOW); 
digitalWrite(led8,LOW); 
delay(1000);
  
}




    if(comando.indexOf("led1")>= 0){
    digitalWrite(led1,!digitalRead(led1)); //faz o contrario
  } 
  if(comando.indexOf("led2")>= 0){
    digitalWrite(led2,!digitalRead(led2)); //faz o contrario
  } 
  if(comando.indexOf("led3")>= 0){
    digitalWrite(led3,!digitalRead(led3)); //faz o contrario
   }
       if(comando.indexOf("led4")>= 0){
    digitalWrite(led4,!digitalRead(led4)); //faz o contrario
  } 
  if(comando.indexOf("led5")>= 0){
    digitalWrite(led5,!digitalRead(led5)); //faz o contrario
  } 
  if(comando.indexOf("led6")>= 0){
    digitalWrite(led6,!digitalRead(led6)); //faz o contrario
   }
  if(comando.indexOf("led7")>= 0){
    digitalWrite(led7,!digitalRead(led7)); //faz o contrario
  } 
  if(comando.indexOf("led8")>= 0){
    digitalWrite(led8,!digitalRead(led8)); //faz o contrario
  } 

     
    
  }
}

