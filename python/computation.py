#####################
# Google's Intro TF #
# course.           #
#####################

# computational.py


# To compress warnings.
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'



import tensorflow as tf

''' begin and end Function definitions  '''

def begin():
    print('\ncomputational.py\n')
        
def end():
    print('\nFin.\n')
 


''' Start of Program '''
begin()
# Simple Addition.
node1 = tf.constant(3.0, dtype=tf.float32)
node2 = tf.constant(4.0)

node3 = tf.add(node2, node1)

# Placeholder Example
a = tf.placeholder(tf.float32)
b = tf.placeholder(tf.float32)

node4 = 4 # placeholders work only for non-constant values.

adder_node = tf.add(a,b)     # Shortcuts to aid clean code.
adder_triple = adder_node*3

# Printing session for testing output
with tf.Session() as sess:
    print('node 3:', node3)
    print('sess.run(node 3) :', sess.run(node3))
    print('Expect (3+4)* 3 = 21. Result: ', sess.run(adder_triple, {a: 3, b: 4 } ))
    print('Expect 3+4 = 7. Result: ', sess.run(adder_node, {a: 3, b: 4 } ) )
    print('Expect 4+4 = 8. Result: ', sess.run(adder_node, {a: node4, b: node4 } ) )

#######################################
#
# Linear Regression Model (y = Wx + b)
#
#######################################
W = tf.Variable([.3], dtype=tf.float32)
b = tf.Variable([-.3], dtype=tf.float32)
x = tf.placeholder(tf.float32)
y = tf.placeholder(tf.float32)

linear_model = W*x + b
squared_deltas = tf.square(linear_model - y)
loss = tf.reduce_sum(squared_deltas)

init = tf.global_variables_initializer() # Initializa all variables in two steps. Step 2: sess.run(init) 

# Assign new values to the variables w and b
fixw = tf.assign(W, [-1])
fixb = tf.assign(b, [1.])


# Printing session for testing output
with tf.Session() as sess:
    sess.run(init) # Step 2 of initializing ariables
    sess.run([fixw, fixb]) # overwriting variable values.
    print('Output of Linear Model with x=[1,2,3,4] ==> ', sess.run(linear_model, {x: [1,2,3,4]}))
    print('Loss Function from Linear Model with x=[1,2,3,4], y=[0,-1,-2,-3] ==> ', sess.run(loss, {x: [1,2,3,4], y: [0,-1,-2,-3]}))



#########################################
#
#   Training. tf.Train API
#
#########################################
optimizer = tf.train.GradientDescentOptimizer(0.01)
train = optimizer.minimize(loss)

#training data
x_train = [1,2,3,4]
y_train = [0,-1,-2,-3] 

with tf.Session() as sess:
    sess.run(init) # set W and b to default incorrect values
    
    for i in range(1000):
        sess.run( train, { x: x_train, y: y_train })

    curr_W, curr_b, curr_loss = sess.run([W, b, loss], {x: x_train, y: y_train} )
    
    # Display results
    print('Trained values for W and b, and loss function:')
    print('W: %s b: %s loss: %s '% (curr_W, curr_b, curr_loss))

###############################################
#
#     tf.estimator
#
###############################################

# For data manipulation, we need numpy
import numpy as np

# Declare list of features.
feature_columns = [tf.feature_column.numeric_column('x', shape = [1])]

# An estimator for training and evaluation.
estimator = tf.estimator.LinearRegressor(feature_columns=feature_columns)

# Reading and setting up data sets.
# Two data sets, one for training and the other for evaluation.
# num_epochs = How many batches of data.
x_train_ = np.array([1., 2., 3., 4.])
y_train_ = np.array([0., -1., -2., -3.])
x_eval_ = np.array([2., 5., 8., 1.])
y_eval_ = np.array([-1.01, -4.1, -7, 0.])

input_fn = tf.estimator.inputs.numpy_input_fn(
        {'x': x_train_}, y_train_, batch_size=4, num_epochs=None, shuffle=True)
train_input_fn = tf.estimator.inputs.numpy_input_fn(
        {'x': x_train_}, y_train_, batch_size=4, num_epochs=1000, shuffle=False)
eval_input_fn = tf.estimator.inputs.numpy_input_fn(
        {'x': x_eval_}, y_eval_, batch_size=4, num_epochs=1000, shuffle=False)

# Invoke the training steps and by calling the train method, passing the input
# and number of steps.
estimator.train(input_fn=input_fn, steps=1000)

# Makiah Makiah Makiah Makiah Makiah

# Evaluate the performance of the model
train_metrics = estimator.evaluate(input_fn=train_input_fn)
eval_metrics = estimator.evaluate(input_fn=eval_input_fn)

print('train metrics: %r' % train_metrics)
print('eval metrics: %r' % eval_metrics)


end()
