# custom_estimator_linear_regression.py

# tf.estimator.LinearRegression is a subclass of tf.estimator.Estimator.
# To make a custom model, one can either subclass this or provide Estimator
# with function (model_fn) that defines how tf.estimator evaluates predictions,
# training steps and loss
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

import numpy as np
import tensorflow as tf


''' defining begin and end functions. '''
def begin():
    print('\ncustom_estimator_linear_regression.py\n')

def end():
    print('\nFin.\n')


''' Start of program '''
begin()

# Only one real-valued feature exists...
def model_fn(features, labels, mode):
    # Building the model, and predict values
    W = tf.get_variable('W', [1], dtype=tf.float64)
    b = tf.get_variable('b', [1], dtype=tf.float64)
    y = W * features['x'] + b
    # Loss sub-graph
    loss = tf.reduce_sum(tf.square(y-labels))
    # Training sub-graph
    global_step = tf.train.get_global_step()
    optimizer = tf.train.GradientDescentOptimizer(0.01)
    train = tf.group(optimizer.minimize(loss),
                    tf.assign_add(global_step, 1))
    # EstimatorSpec connects the built graph to the appropriate functionality
    return tf.estimator.EstimatorSpec(
            mode=mode,
            predictions=y,
            loss=loss,
            train_op=train)

estimator = tf.estimator.Estimator(model_fn=model_fn)

# Define data sets.
x_train = np.array([1., 2., 3., 4.])
y_train = np.array([0., -1., -2., -3.])
x_eval = np.array([2., 5., 8., 1.])
y_eval = np.array([-1.01, -4.1, -7, 0.])

input_fn = tf.estimator.inputs.numpy_input_fn(
        {'x': x_train}, y_train, batch_size=4, num_epochs=None, shuffle=True)
train_input_fn = tf.estimator.inputs.numpy_input_fn(
        {'x': x_train}, y_train, batch_size=4, num_epochs=1000, shuffle=False)
eval_input_fn = tf.estimator.inputs.numpy_input_fn(
        {'x': x_eval}, y_eval, batch_size=4, num_epochs=1000, shuffle=False)

# Training...
estimator.train(input_fn=input_fn)

# Evaluation
train_metrics = estimator.evaluate(input_fn=train_input_fn)
eval_metrics = estimator.evaluate(input_fn=eval_input_fn)
# Print
print('train metrics: %r' % train_metrics)
print('eval metrics: %r' % eval_metrics)





end()
