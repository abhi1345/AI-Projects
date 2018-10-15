#Long Short Term Memory Neural Network for Stock Prediction

#Step 0, import statements
from keras.layers.core import Dense, Activation, Dropout
from keras.layers.recurrent import LSTM
from keras.models import Sequential
import lstm, time


# In[17]:


#Step 1, load data
x_train, y_train, x_test, y_test = lstm.load_data('sp500.csv', 50, True)


# In[18]:


#Step 2, Build model
model = Sequential()

model.add(LSTM(input_dim=1, output_dim=50, return_sequences=True))

model.add(Dropout(0.2))

model.add(LSTM(100, return_sequences=False))

model.add(Dropout(0.2))

model.add(Dense(output_dim=1))

model.add(Activation('linear'))

start = time.time()

model.compile(loss='mse', optimizer='rmsprop')

print('compilation time:', time.time() - start)


# In[19]:


#Step 3, train model
model.fit(x_train, y_train, batch_size=512, nb_epoch=1, validation_split=0.05)


# In[20]:


#Step 4, plot predictions
predictions = lstm.predict_sequences_multiple(model, x_test, 50, 50)
lstm.plot_results_multiple(predictions, y_test, 50)
