<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex xs12>
        <v-img :src="require('../assets/logo.svg')" class="my-3" contain height="200"></v-img>
      </v-flex>

      <v-flex mb-4>
        <h1 class="display-2 font-weight-bold mb-3">Mobile Computing Course Attendance</h1>
        <p class="subheading font-weight-regular">
          Please note that the system has a unique key for each student.<br>Cheating will be detected. So, Play nice. :)
        </p>
      </v-flex>

      <v-flex mb-5 xs12>
        <v-layout justify-center>
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-text-field v-model="lectureNumber" label="Lecture Number" type="number" required></v-text-field>
            <v-btn color="success" class="mr-4" @click="submit">Submit</v-btn>
          </v-form>
        </v-layout>
      </v-flex>

      <v-flex mb-5 xs12>
        <v-layout justify-center>
          <img :src="encodedQR" />
        </v-layout>
      </v-flex>
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
    encodedQR: ""
  }),
  methods: {
    submit: function() {
      console.log("this.lectureNumber", this.lectureNumber);
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
        .post("http://localhost:3000/qrcode", data)
        .then(response => {
          this.encodedQR = response.data;
          console.log(response);
          console.log(this.encodedQR);
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>
