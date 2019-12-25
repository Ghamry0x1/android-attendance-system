const mongoose = require('mongoose');

const Attendance_schema = mongoose.Schema({
  students: {
    type: [mongoose.Schema.Types.ObjectId],
    ref: 'User'
  },
  weekNumber: {
    type: Number,
    unique: true
  }
});

const Attendance = mongoose.model('Attendance', Attendance_schema);
module.exports = Attendance;

mongoose.set('useCreateIndex', true);
