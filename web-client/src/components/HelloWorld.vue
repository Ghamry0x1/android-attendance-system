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

      <v-dialog v-model="dialog1" fullscreen hide-overlay transition="dialog-bottom-transition">
        <v-card>
          <v-toolbar dark color="primary">
            <v-btn icon dark @click="dialog1 = false">
              <v-icon>mdi-close</v-icon>
            </v-btn>
            <v-toolbar-title>Attendance List</v-toolbar-title>
          </v-toolbar>
          <v-list three-line subheader>
            <v-data-table :headers="headers" :items="attendanceList" :items-per-page="10">
              <template v-slot:top>
                <v-toolbar flat color="white">
                  <v-toolbar-title>Lecture {{lectureNumber}}</v-toolbar-title>
                  <v-divider class="mx-4" inset vertical></v-divider>
                  <v-spacer></v-spacer>
                  <v-dialog v-model="dialog2" max-width="500px">
                    <template v-slot:activator="{ on }">
                      <v-btn color="primary" dark class="mb-2" v-on="on">Add Student</v-btn>
                    </template>
                    <v-card>
                      <v-card-title>
                        <span class="headline">Add Student</span>
                      </v-card-title>

                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12" sm="6" md="4">
                              <v-text-field v-model="editedItem.studentID" label="Student ID"></v-text-field>
                            </v-col>
                          </v-row>
                        </v-container>
                      </v-card-text>

                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                        <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-toolbar>
              </template>
              <!-- <template v-slot:item.action="{ item }">
                <v-icon small class="mr-2" @click="editItem(item)">fas fa-edit</v-icon>
                <v-icon small @click="deleteItem(item)">fas fa-trash</v-icon>
              </template>-->
            </v-data-table>
          </v-list>
        </v-card>
        <v-snackbar v-model="snackbar">
          {{ addStudentResponseMsg }}
          <v-btn color="pink" text @click="snackbar = false">Close</v-btn>
        </v-snackbar>
      </v-dialog>
      <v-snackbar v-model="atsnackbar">
          {{ viewAttendanceListResponseMsg }}
          <v-btn color="pink" text @click="atsnackbar = false">Close</v-btn>
        </v-snackbar>
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
    dialog1: false,
    dialog2: false,
    attendanceList: [],
    headers: [
      {
        text: "ID",
        align: "left",
        sortable: true,
        value: "studentID"
      }
      // { text: 'Actions', value: 'action', sortable: false },
      // { text: "Name", value: "name" }
    ],
    // editedIndex: -1,
    editedItem: {
      studentID: ""
    },
    defaultItem: {
      studentID: ""
    },
    addStudentResponseMsg: "",
    viewAttendanceListResponseMsg: "",
    snackbar: false,
    atsnackbar: false
  }),
  methods: {
    submit() {
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
        .post("http://104.154.52.199:3000/qrcode", data)
        .then(response => {
          this.encodedQR = response.data;
        })
        .catch(err => {
          console.error(err);
        });
    },
    viewAttendanceList() {
      let attendanceAPI;
      // if(this.lectureNumber == '') attendanceAPI = 'http://104.154.52.199:3000/attendance';
      // else attendanceAPI = `http://104.154.52.199:3000/attendance?week=${this.lectureNumber}`;
      attendanceAPI = `http://104.154.52.199:3000/attendance?week=${this.lectureNumber}`;
      axios
        .get(attendanceAPI)
        .then(response => {
          if (response.data == null) {
            this.viewAttendanceListResponseMsg = `Please submit a lecture first to see attendance list`;
            this.atsnackbar = true;
          } else {
            this.dialog1 = true;
            this.attendanceList = response.data.students;
          }
        })
        .catch(err => {
          console.error(err);
        });
    },
    close() {
      this.dialog2 = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        // this.editedIndex = -1;
      }, 300);
    },
    save() {
      const data = {
        course: {
          name: "Mobile Computing",
          code: "CSE437"
        },
        lecture: {
          number: this.lectureNumber,
          date: Date.now()
        },
        student: {
          id: this.editedItem.studentID
        }
      };
      axios
        .post("http://104.154.52.199:3000/attendance", data)
        .then(response => {
          this.addStudentResponseMsg = `Student ${this.editedItem.studentID} is added successfully`;
          this.attendanceList = response.data.students;
          this.snackbar = true;
          this.dialog1 = false;
        })
        .catch(err => {
          this.addStudentResponseMsg = err.response.data;
          this.snackbar = true;
        });
      this.close();
    }
  }
};
</script>
