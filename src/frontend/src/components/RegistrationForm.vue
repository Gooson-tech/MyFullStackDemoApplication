<template>
    <form @submit.prevent="handleSubmit">
      <!--
      the 'required' keyword is for client-side validation.
      there is server validation as well
      To test serverside just remove required
      -->
      <label>First Name :</label>
      <input
          type="text"
          v-model="firstName"
          pattern="[a-zA-Z'-'\s]*"
          required
      />
      <label>Last Name :</label>
      <input type="text" v-model="lastName" pattern="[a-zA-Z'-'\s]*" required />

      <label>Email :</label>
      <input type="email" v-model="email" required />

      <label>Semester :</label>
      <select id="semester" v-model="semester" required>
        <option value="" disabled></option>
        <option value="spring2022">Spring</option>
        <option value="summer2022">Summer</option>
        <option value="fall2022">Fall</option>
      </select>

      <label>Course :</label>
      <select id="course" v-model="course" required>
        <option value="" disabled></option>
        <option v-for="course in courses" v-bind:key="course">
          {{course}}
        </option>
      </select>

      <label>Note for Professor :</label>
      <textarea v-model="note" rows="5" cols="33"> </textarea>

      <div>
        <button type="submit">Submit</button>
      </div>

      <span id="errorSpan" v-if="Array.isArray(errors)">{{errors.join(", ") }}</span>
      <span id="errorSpan" v-else> {{errors}} </span>

      <span id="successSpan">{{successMessage}}</span>
    </form>

    <p>Debug</p>
    <p>These Classes Are Full: French I and Botany  (not in database/taken out)</p>
    <p>First Name: {{ firstName }}</p>
    <p>Last Name: {{ lastName }}</p>
    <p>Email: {{ email }}</p>
    <p>Semester: {{ semester  }}</p>
    <p>Course: {{ course }}</p>
    <p>Note: {{ note  }}</p>
</template>
<script>

import axios from "axios";
export default {
  data() {
    return {
      courseList: {
        "spring2022": [""],
        "fall2022": [""],
        "summer2022": [""]
     /* OLD  "fall2022": ["Algebra I", "Economics I", "Algebra II", "Earth science", "French I"],
        "summer2022": ["American literature", "Life science", "Comparative literature", "Botany"]*/
      },
      firstName: '',
      lastName:'',
      email: '',
      semester: '',
      course: '',
      note: '',
      returned: '',
      courses: [],
      errors : [],
      successMessage : '',
    }
  },
  methods: {
    handleSubmit() {
      let user = {
        firstName : this.firstName,
        lastName : this.lastName,
        email : this.email,
        semester : this.semester,
        course : this.course,
        note : this.note
      }
      //reset messages
      this.errors = [];
      this.successMessage = '';

      axios.post("http://localhost:3000/api/sendRegistration", user)
          .then(resp=> {
            console.log(resp);
            //if course is given back empty it doesnt exist in the database (probably not the best way to do this)
            if (resp.data.course===""){ this.errors = ["Sorry, selected class is full!"];}
            else{ this.successMessage = "Success, Form submitted!";}
          } )
          .catch(error => {
            if (error.response) { // The request was made and the server responded with a status code that falls out of the range of 2xx
              this.errors = Object.values(error.response.data);
              console.log(error.response.data);
            } /*The request was made but no response was received*/
            else if (error.request) { console.log(error.request); }
            /* Something happened in setting up the request that triggered an Error */
            else { console.log('Error', error.message); }
          });
    },
  },
  watch: {
    semester(selected) {
      // clear previously selected values
      this.course = '';
      this.courses = [];
      //set courses depending on the selected semester
      this.courses = this.courseList[selected];
    }

  },
  mounted: function () {

    fetch("/api/getClassesInSpring2022")
        .then(response => response.text())
        .then(spring => {
          let springParsed = JSON.parse(spring);
          let coursePrint = [];
          springParsed.forEach(
              (course) => coursePrint.push(`${(course.name)}, Section: ${(course.section)}, Credits: ${course.credit}`)
          );
          this.courseList.spring2022 = coursePrint;
        });

    fetch("/api/getClassesInSummer2022")
        .then(response => response.text())
        .then(summer => {
          let summerParsed = JSON.parse(summer);
          let coursePrint = [];
          summerParsed.forEach(
              (course) => coursePrint.push(`${(course.name)}, Section: ${(course.section)}, Credits: ${course.credit}`)
          );
          this.courseList.summer2022 = coursePrint;
        });

    fetch("/api/getClassesInFall2022")
        .then(response => response.text())
        .then(fall => {
          let fallParsed = JSON.parse(fall);
          let coursePrint = [];
          fallParsed.forEach(
              (course) => coursePrint.push(`${(course.name)}, Section: ${(course.section)}, Credits: ${course.credit}`)
          );
          this.courseList.fall2022 = coursePrint;
        });

  }
}
</script>

<style scoped>

form {
  max-width: 600px;
  margin: 30px auto;
  background: #fff;
  text-align: left;
  padding: 20px;
  border-radius: 10px;
}

#successSpan {
  color: #002aff;
  display: inline-block;
  margin: 25px 0 15px;
  font-size: 20px;
}

#errorSpan {
  color: #ff4040;
  display: inline-block;
  margin: 25px 0 15px;
  font-size: 20px;
}

label {
  color: #aaa;
  display: inline-block;
  margin: 25px 0 15px;
  text-transform: uppercase;
}

input, select {
  display: block;
  padding: 10px 6px;
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid #ddd;
  color: #555;
}

label {
  color: #aaa;
  margin: 25px 0 15px;
  display: inline-block;
  text-transform: uppercase;
}

button {
  background: rgb(44, 121, 44);
  border: 0;
  padding: 10px 20px;
  color: white;
  border-radius: 20px;
}

textarea {
  outline: #2c2c2c;
  padding: 10px 6px;
  width: 100%;
  box-sizing: border-box;
  color: #555;
  resize: none;
}
</style>
