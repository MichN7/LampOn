
#include <SoftwareSerial.h>
int led=8;
char rec;
char valor=0;
SoftwareSerial blue(2,3);//tx rx
void setup() {
 blue.begin(9600);
pinMode(led,OUTPUT);
Serial.begin(9600);
}

void loop() {
 // Serial.print("23");
   if(blue.available()>0){
    rec=blue.read();
      blue.println(rec);
      if(rec=='1'){
      digitalWrite(led,HIGH);
      }
      else if(rec=='2'){
        digitalWrite(led,HIGH);
        }
        else if(rec=='3'){
          digitalWrite(led,LOW);
          }
      else{
      digitalWrite(led,LOW);
      }
    }
  


}
