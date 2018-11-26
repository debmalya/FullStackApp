import React, { Component } from 'react';
import axios from 'axios';
import ReactTable from 'react-table'
import "react-table/react-table.css"; 

class Main extends Component {

  ping() {
    axios.get("http://localhost:8080/test").then(res => {
    alert("Received Successful response from server!" + res);
  }, err => {
    alert("Server rejected response with: " + err);
  });
  }

  fileUpload(e){
    let files = e.target.files
    
    let reader = new FileReader()
   
    reader.readAsText(files[0])
    reader.onload=(e)=>{
      
      const url = "http://localhost:8080/test"
      const formData = {file:e.target.result}
      return axios.post(url,formData).then(response=>{
       
        this.setState({
            isLoaded: true,
            employees: response.data.employeeList,
            errors: response.data.errorRecordsList,
        }, err => {
          //console.log("Server rejected response with: " ,err);
        })
      })
    }
  }

  constructor(props){
     super(props)
     this.state={
       employees:[],
       errors:[],
       isLoaded:false,
     }
  }

  componentDidMount(){
  //  console.log("component did mount")
  }

  render() {
   

    const columns = [
       {id:'name',Header: 'Name',accessor: 'name'},
       {Header: 'Department',accessor: 'department'},
       {Header:'Designation',accessor:'designation'},
       {Header:'Salary',accessor:'salary'},
       {Header:'Joining Date',accessor:'joiningDate'}]

    const error_columns = [
        {id:'record',Header: 'Record',accessor: 'record'},
        {Header: 'Error Message',accessor: 'errorMsg'},
        {Header:'Record Number',accessor:'recordNo'}]   
    return (
      <div className="Main">
          <h1>Welcome to React</h1>
            <div>
              <input type="file" id="myFile" onChange={(e)=>this.fileUpload(e)}></input>
            </div>
            <div>
              <h2>Valid Records</h2>
            <ReactTable
                   
                  data={this.state.employees}
                  columns={columns}
              />
            </div>
            <div>
              <h2>Errors</h2>
            <ReactTable
                   
                  data={this.state.errors}
                  columns={error_columns}
              />
            </div>
      </div>
    );
  }
}

export default Main;