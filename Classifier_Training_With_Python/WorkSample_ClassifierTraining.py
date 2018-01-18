import numpy as np
import matplotlib.pyplot as plt
from sklearn import tree
from sklearn.neighbors import KNeighborsClassifier
from sklearn.cross_validation import train_test_split
from sklearn.metrics import accuracy_score

#Classifier instantiation
clf1 = tree.DecisionTreeClassifier()
clf2 = KNeighborsClassifier()

#Instantiation of arrays
datalist = []
targetlist = []

a_height = []
b_height = []
c_height = []

a_weight = []
b_weight = []
c_weight = []

a_age = []
b_age = []
c_age = []

#Population of arrays with generated data
for i in xrange(0, 50):
    dataentry = []
    height = round(35 + 4 * np.random.randn(), 2)
    weight = round(100 + 4 * np.random.randn(), 2)
    age = round(50 + 4 * np.random.randn(), 0)
    # height
    dataentry.append(height)
    a_height.append(height)
    # weight
    dataentry.append(weight)
    a_weight.append(weight)
    # age
    dataentry.append(age)
    a_age.append(age)

    datalist.append(dataentry)
    targetlist.append('a')

for i in xrange(0, 50):
    dataentry = []
    height = round(25 + 2 * np.random.randn(), 2)
    weight = round(100 + 2 * np.random.randn(), 2)
    age = round(70 + 4 * np.random.randn(), 0)
    # height
    dataentry.append(height)
    b_height.append(height)
    # weight
    dataentry.append(weight)
    b_weight.append(weight)
    # age
    dataentry.append(age)
    b_age.append(age)

    datalist.append(dataentry)
    targetlist.append('b')

for i in xrange(0, 50):
    dataentry = []
    height = round(35 + 8 * np.random.randn(), 2)
    weight = round(100 + 4 * np.random.randn(), 2)
    age = round(60 + 4 * np.random.randn(), 0)
    # height
    dataentry.append(height)
    c_height.append(height)
    # weight
    dataentry.append(weight)
    c_weight.append(weight)
    # age
    dataentry.append(age)
    c_age.append(age)

    datalist.append(dataentry)
    targetlist.append('c')

#Visualization of randomly generated data distributions
plt.hist([a_height, b_height, c_height], stacked=True, color=['r', 'b', 'g'], width=2)
plt.show()

plt.hist([a_weight, b_weight, c_weight], stacked=True, color=['r', 'b', 'g'], width=1)
plt.show()

plt.hist([a_age, b_age, c_age], stacked=True, color=['r', 'b', 'g'], width=2)
plt.show()

#Partition of dataset into test and training data
X = datalist
y = targetlist
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = .5)

#Classifier model fit
predictor1 = clf1.fit(X_train, y_train)
predictor2 = clf2.fit(X_train, y_train)

#Prediction based on model
prediction1 = predictor1.predict(X_test)
prediction2 = predictor2.predict(X_test)

print "Decision Tree accuracy:" + str(accuracy_score(y_test, prediction1))
print "Nearest Neighbor accuracy:" + str(accuracy_score(y_test, prediction2))

#-------------Decision Tree Visualization----------------------------
from sklearn.externals.six import StringIO
import pydotplus

dot_data = StringIO()
tree.export_graphviz(clf1,
                        out_file=dot_data,
                        feature_names=['height','weight','age'],
                        class_names=['a','b','c'],
                        filled=True, rounded=True,
                        impurity=False)
graph = pydotplus.graph_from_dot_data(dot_data.getvalue())
graph.write_pdf("decisiontree.pdf")
graph.write_png("decisiontree.png")