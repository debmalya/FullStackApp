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
    const data = this.employees

    const columns = [
       {id:'name',Header: 'Name',accessor: 'name'},
       {Header: 'Department',accessor: 'department'},
       {Header:'Designation',accessor:'designation'},
       {Header:'Salary',accessor:'salary'},
       {Header:'Joining Date',accessor:'joiningDate'}]
    return (
      <div className="Main">
          <h1>Welcome to React</h1>
            <div>
              <input type="file" id="myFile" onChange={(e)=>this.fileUpload(e)}></input>
            </div>
            <div>
            <ReactTable
                   
                  data={this.state.employees}
                  columns={columns}
              />
            </div>
      </div>
    );
  }
}

export default Main;
