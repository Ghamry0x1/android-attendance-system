const mongoose = require('mongoose');

const User_schema = mongoose.Schema({
  studentID: {
    type: String,
    unique: true
  },
  name: {
    type: String
  }
});

const User = mongoose.model('User', User_schema);
module.exports = User;

mongoose.set('useCreateIndex', true);
