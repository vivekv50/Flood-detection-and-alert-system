#include <LiquidCrystal_I2C.h>
LiquidCrystal_I2C lcd(0x27, 16, 2);
const int buzzer = 3;
void setup() {
  Serial.begin(9600);
  lcd.init();
  lcd.backlight();
  pinMode(buzzer,OUTPUT);
}
void loop() {
  int value = analogRead(A0);
  lcd.setCursor(0, 0);
  lcd.print("Value :");
  lcd.print(value);
  lcd.print("   ");
  Serial.println(value);
  lcd.setCursor(0, 1);
  lcd.print("W Level :");


  if (value == 0) {
    lcd.print("Empty ");
  } else if (value > 1 && value < 350) {
    lcd.print("LOW   ");
  } else if (value > 350 && value < 510) {
    lcd.print("Medium");
  } else if (value > 510){
    lcd.print("HIGH  ");
    tone(buzzer,400,1000);
    Serial.begin(9600);
    Serial.println("AT+CMGF=1");
    delay(1000);
    Serial.println("AT+CMGS=\"+91XXXXXXXXX\"\r");
    delay(1000);
    Serial.println("water in a danger level");
    delay(1000);
    Serial.println("location:- mumbai");
    delay(1000);
    Serial.println((char)26);
  }
}
