const express = require('express');
const QRCode = require('qrcode');
const router = express.Router();

router.get('/', (req, res) => {
  res.json('index');
});

router.post('/qrcode', (req, res) => {
  const courseName = req.body.course.name;
  const courseCode = req.body.course.code;
  const lectureNumber = req.body.lecture.number;
  const lectureDate = req.body.lecture.date;

  const data = {
    courseName,
    courseCode,
    lectureNumber,
    lectureDate
  };

  const opts = {
    errorCorrectionLevel: 'H',
    type: 'image/jpeg',
    quality: 0.3,
    margin: 1
  };

  QRCode.toDataURL(JSON.stringify(data), opts)
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
  const studentId = req.body.student.id;

  const data = {
    courseName,
    courseCode,
    lectureNumber,
    lectureDate,
    studentId
  };

  res.status(200).json(data);
});

module.exports = router;
