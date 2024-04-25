// import logo from './logo.svg';
import './App.css';
import Login from './components/student/login/Login';
import SignUp from './components/student/signup/SignUp';
import Home from './components/student/home/Home';
import NewRequest from './components/student/new_request/NewRequest';
import MyRequest from './components/student/my-request/MyRequest';
import {
  BrowserRouter,
  Route,
  Routes,
} from "react-router-dom";
import AdminLogin from './components/admin/login/AdminLogin';
import AdminHome from './components/admin/home/AdminHome';
import Approval from './components/admin/approval/Approval';
import LeaveRequest from './components/admin/leave-request/LeaveRequest';
import SearchRequest from './components/admin/search-request/SearchRequest';

function App() {
  
  return (
    <>
    <BrowserRouter>
    <Routes>
      <Route path='/home' element={<Home/>} />
      <Route path='/' element={<Login/>} />
      <Route path='/new-request' element={<NewRequest/>} />
      <Route path='/my-request' element={<MyRequest/>} />
      <Route path='/signup' element={<SignUp/>} />
      <Route path='/admin' element={<AdminLogin/>} />
      <Route path="/admin-home" element={<AdminHome />}/>
      <Route path="/leave-request" element={<LeaveRequest />}/>
      <Route path="/approve" element={<Approval />}/>
      <Route path='/search-request' element={<SearchRequest />} />
    </Routes>
    </BrowserRouter>
        
    </>
    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    // </div>
  );
}

export default App;
