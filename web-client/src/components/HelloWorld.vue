<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex xs12>
        <v-img :src="require('../assets/logo.svg')" class="my-3" contain height="200"></v-img>
      </v-flex>

      <v-flex mb-4>
        <h1 class="display-2 font-weight-bold mb-3">Mobile Computing Course Attendance</h1>
        <p class="subheading font-weight-regular">
          Please note that the system has a unique key for each student.
          <br />Cheating will be detected. So, be nice. :)
        </p>
      </v-flex>

      <v-flex mb-5 xs12>
        <v-layout justify-center>
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-text-field v-model="lectureNumber" label="Lecture Number" type="number" required></v-text-field>
            <v-btn color="success" class="mr-4" @click="submit">Submit</v-btn>
            <v-btn color="primary" class="mr-4" @click="viewAttendanceList">View attendance list</v-btn>
          </v-form>
        </v-layout>
      </v-flex>

      <v-flex mb-5 xs12>
        <v-layout justify-center>
          <img :src="encodedQR" />
        </v-layout>
      </v-flex>

      <v-dialog v-model="dialog" scrollable max-width="500px">
        <v-card>
          <v-card-title>Lecture {{lectureNumber}} - Attendance List</v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <!-- height="300px" -->
            <v-simple-table>
              <template v-slot:default>
                <thead>
                  <tr>
                    <th class="text-left">ID</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="item in attendanceList" :key="item._id">
                    <td>{{ item.studentID }}</td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>
  </v-container>
</template>

<script>
/*eslint-disable */
import axios from "axios";

export default {
  name: "HelloWorld",
  data: () => ({
    lectureNumber: "",
    valid: true,
    encodedQR: "",
    dialog: false,
    attendanceList: []
  }),
  methods: {
    submit: function() {
      const data = {
        course: {
          name: "Mobile Computing",
          code: "CSE437"
        },
        lecture: {
          number: this.lectureNumber,
          date: Date.now()
        }
      };
      axios
        .post("http://http://104.154.52.199:3000/qrcode", data)
        .then(response => {
          this.encodedQR = response.data;
        })
        .catch(err => {
          console.error(err);
        });
    },
    viewAttendanceList: function() {
      if (this.lectureNumber != "") this.dialog = true;
      let attendanceAPI;
      // if(this.lectureNumber == '') attendanceAPI = 'http://http://104.154.52.199:3000/attendance';
      // else attendanceAPI = `http://http://104.154.52.199:3000/attendance?week=${this.lectureNumber}`;
      attendanceAPI = `http://http://104.154.52.199:3000/attendance?week=${this.lectureNumber}`;
      axios
        .get(attendanceAPI)
        .then(response => {
          this.attendanceList = response.data.students;
        })
        .catch(err => {
          console.error(err);
        });
    }
  }
};
</script>
