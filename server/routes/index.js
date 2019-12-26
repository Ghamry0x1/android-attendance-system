const express = require('express');
const QRCode = require('qrcode');

const User = require('../models/user');
const Attendance = require('../models/attendance');

const router = express.Router();

router.post('/register', (req, res) => {
  const studentID = req.body.student.id;
  const student = new User({ studentID });
  student
    .save()
    .then(savedUser => res.status(200).json(savedUser))
    .catch(err => res.status(400).json(err));
});

router.post('/login', (req, res) => {
  const studentID = req.body.student.id;
  User.findOne({ studentID: studentID })
    .then(user => {
      if (user) res.status(200).json('success');
      else res.status(400).json('failed');
    })
    .catch(err => res.status(400).json(err));
});

router.post('/qrcode', (req, res) => {
  const opts = {
    errorCorrectionLevel: 'H',
    type: 'image/jpeg',
    quality: 0.3,
    margin: 1
  };

  QRCode.toDataURL(JSON.stringify(req.body), opts)
    .then(qrcode => {
      res.status(200).json(qrcode);
    })
    .catch(err => {
      res.status(400).json(err);
    });
});

router.post('/attendance', (req, res) => {
  const courseName = req.body.course.name;
  const courseCode = req.body.course.code;
  const lectureNumber = req.body.lecture.number;
  const lectureDate = req.body.lecture.date;
  const studentID = req.body.student.id;

  const data = {
    courseName,
    courseCode,
    lectureNumber,
    lectureDate,
    studentID
  };

  Attendance.findOne({ weekNumber: lectureNumber })
    .then(record => {
      if (record) {
        console.log('inside if');
        User.findOne({ studentID: studentID })
          .then(user => {
            if (user) {
              if (record.students.includes(user._id))
                res.status(400).json('already submitted');
              else {
                record.students.push(user);
                record
                  .save()
                  .then(savedRec => res.status(200).json(savedRec))
                  .catch(err => res.status(400).json(err));
              }
            } else res.status(400).json('failed');
          })
          .catch(err => res.status(400).json(err));
      } else {
        const newRec = new Attendance({
          weekNumber: lectureNumber
        });
        User.findOne({ studentID: studentID })
          .then(user => {
            if (user) {
              newRec.students.push(user);
              newRec
                .save()
                .then(savedRec => res.status(200).json(savedRec))
                .catch(err => res.status(400).json(err));
            } else res.status(400).json('failed');
          })
          .catch(err => res.status(400).json(err));
      }
    })
    .catch(err => res.status(400).json(err));
});

router.get('/attendance', (req, res) => {
  let week;
  req.query.week ? (week = req.query.week) : null;
  if (week)
    Attendance.findOne({ weekNumber: week })
      .populate({ path: 'students', model: 'User' })
      .then(record => res.status(200).json(record))
      .catch(err => res.status(400).json(err));
  else {
    Attendance.find({})
      .populate({ path: 'students', model: 'User' })
      .then(records => res.status(200).json(records))
      .catch(err => res.status(400).json(err));
  }
});

module.exports = router;
