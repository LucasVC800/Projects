Gold = read.csv("C:/Users/Lucas/Desktop/gold.csv")

Gold$t = 1:563

summary(Gold$Value)

acf(Gold$Value, main="ACF for Value")
pacf(Gold$Value)

plot.ts(Gold$Value, main="Monthly Average Value of Gold (USD)", ylab="Value", xlab="Time")

adf.test(Gold$Value[100:300])
#Checking to see if the more "flat" middle period is stationary.

Value.lag1 = shift(Gold$Value, 1)
Value.delta = Gold$Value - Value.lag1

plot(Value.delta)

acf(Value.delta, na.action=na.pass, main="ACF for 'Value' Differenced")
pacf(Value.delta, na.action=na.pass, main="PACF for 'Value' Differenced")

Arima1 = arima(Gold$Value, order=c(1,1,0))
Arimatest = arima(Gold$Value[1:200], order=c(1,1,0))
resid1 = resid(Arima1)

pred=predict(Arima1, 24)

predtest = predict(Arimatest, 24)
#Attempting to do a prediction from the 200 point, as though I did not have
#the data from this point on.

plot(Gold$Value)
lines(pred$pred, col=2)


acf(resid1)
pacf(resid1)

plot(resid1, ylab="Residuals", main="Plot of Residuals for Arima(1,1,0)")

qqnorm(resid1, main="Normal Q-Q Plot For Arima (1,1,0) of Value")
qqline(resid1)
plot(density(resid1), main="Density Plot For Arima (1,1,0) of Value")

resid1.squared = resid1^2
resid1.lag1 = shift(resid1, 1)

sum(resid1.squared)

acf(resid1.squared)
pacf(resid1.squared)

plot(resid1.squared)

value.hat = Gold$Value + resid1

plot(Gold$Value, main="Estimated 'Value' vs Actual Observations", ylab="Value", xlab="Time")
lines(value.hat, col=2)

#--Chow Test #1 For Break at t=478------------------------------

Gold$pre_dummy = 1:563
Gold$Dummy <- ifelse(Gold$pre_dummy< 478, 0, 1)

ChowTest1 = lm(Gold$Value ~ Value.lag1 + Gold$Dummy)
summary(ChowTest1)

#--Chow Test #2 For Break at t=478------------------------------

Gold.lower = Gold$Value[1:477]
Gold.higher = Gold$Value[478:563]

acf(Gold.lower)
pacf(Gold.lower)

acf(Gold.higher)
pacf(Gold.higher)

Gold.lower.lag1 = Value.lag1[1:477]
Gold.higher.lag1 = Value.lag1[478:563]

arima.Goldlower = arima(Gold.lower, order=c(1,1,0))
arima.Goldhigher = arima(Gold.higher, order=c(1,1,0))

resid.Goldlower = resid(arima.Goldlower)
resid.Goldhigher = resid(arima.Goldhigher)

SSR.Goldlower = sum(resid.Goldlower^2)
SSR.Goldhigher = sum(resid.Goldhigher^2)
SSR = sum(resid1.squared)

#My F-statistic is 9.65 which surpasses the critical F value when the numerator
#and demoninator have 562 degrees of freedom.

Value.lower = Gold$Value[1:477]
Value.higher = Gold$Value[478:563]

#--Determining Model For Pre-Break----

acf(Value.lower, main="ACF for Value.lower Undifferenced")
pacf(Value.lower)

Value.lower.lag1= shift(Value.lower,1)
Value.lower.delta= Value.lower - Value.lower.lag1

acf(Value.lower.delta, na.action=na.pass, main="ACF for Value.lower Differenced")
pacf(Value.lower.delta, na.action=na.pass, main="ACF for Value.lower Differenced")

arima(Value.lower, order=c(0,1,0))
Arima.lower = arima(Value.lower, order=c(1,1,0))

Arima.lower.res = resid(Arima.lower)

Value.lower.hat = Value.lower + Arima.lower.res

plot.ts(Value.lower)
lines(Value.lower.hat, col=2)

predict(Arima.lower, 24)

adf.test(Value.lower)

qqnorm(Arima.lower.res, main="Normal Q-Q Plot for Arima(1,1,0) of Value.lower")
qqline(Arima.lower.res)
plot(density(Arima.lower.res), main="Density Plot for Arima(1,1,0) of Value.lower")

plot(Arima.lower.res)

Arima.lower.res.squared = (Arima.lower.res^2)

acf(Arima.lower.res.squared, main="ACF of Squared Residuals for Arima(1,1,0)")
pacf(Arima.lower.res.squared, main="PACF of Squared Residuals for Arima(1,1,0)")

garch1 = garch(Arima.lower.res, order=c(0,2))
garch2 = garch(Arima.lower.res, order=c(2,2))

AIC(garch1)
AIC(garch2)

summary(garch1)
summary(garch2)

Garch.lower.resid = resid(garch1)

Value.lower.hat = Value.lower + Garch.lower.resid

plot.ts(Value.lower, ylab="Value", main="Predicted Vs Actual Fit for Value.lower after GARCH(0,1)")
lines(Value.lower.hat, col=2)

qqnorm(Garch.lower.resid, main="Normal Q-Q Plot for Residuals of Garch(0,2)")
qqline(Garch.lower.resid)
plot(density(resid(garch1)))




#--Determining Model for Post-Break-------------------------------------


acf(Value.higher, main="ACF for Value.higher Undifferenced")
pacf(Value.higher)

plot.ts(Value.higher, main="Plot of Value in Value.higher subset", ylab="Value")

Value.higher.lag1= shift(Value.higher,1)
Value.higher.delta= Value.higher - Value.higher.lag1

acf(Value.higher.delta, na.action=na.pass, main="ACF for Value.higher Differenced")
pacf(Value.higher.delta, na.action=na.pass, main="PACF for Value.higher Differenced")

Arima.higher = arima(Value.higher, order=c(0,1,0))
arima(Value.higher, order=c(1,1,0))

Arima.higher.res = resid(Arima.higher)

Value.higher.hat = Value.higher + Arima.higher.res

plot.ts(Value.higher, ylab="Value", main="Predicted Value.higher vs Actual Using Arima(0,1,0)")
lines(Value.higher.hat, col=2)

predict(Arima.higher, 24)

adf.test(Value.higher)


qqnorm(Arima.higher.res, main="Normal Q-Q Plot for Residuals of Arima(0,1,0")
qqline(Arima.higher.res)
plot(density(Arima.higher.res), main="Density Plot for Residuals of Arima(0,1,0)")
