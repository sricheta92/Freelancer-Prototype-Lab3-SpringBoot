import React , {Component} from 'react';
import { withRouter ,Route} from 'react-router-dom';


class NavBeforeLogin extends Component{

  render(){
    return(
      <div>
        <nav className="navbar navbar-default navbar1">
          <div className="container-fluid">
            <div className="navbar-header">
              <a className="navbar-brand a-logo" href="#"><img className = "logo" src = "./images/icon.PNG"></img></a>
            </div>

        <ul className="nav navbar-nav navbar-right before-login-list">
              <li><a onClick={() => { this.props.history.push("/login");}}> Log In </a></li>
              <li ><a onClick={() => { this.props.history.push("/signup");}}> Sign Up </a></li>


         </ul>
       </div>
     </nav>

         <div className="overlay1"><h1>Hire expert freelancers for any job, online</h1></div>
         <div className = "overlay2"><p>Millions of small businesses use Freelancer to turn their ideas into reality.</p></div>
         <div id="myCarousel" className="carousel" data-ride="carousel">
              <ol className="carousel-indicators">
                 <li data-target="#myCarousel" data-slide-to="0" className="active"></li>
                 <li data-target="#myCarousel" data-slide-to="1"></li>
                 <li data-target="#myCarousel" data-slide-to="2"></li>
               </ol>
               <div className="carousel-inner">
                   <div className="item active">
                     <img src="./images/1.JPG"/>
                   </div>

                   <div className="item">
                     <img src="./images/2.JPG"/>
                   </div>

                   <div className="item">
                     <img src="./images/3.JPG"/>
                    </div>
                </div>
              </div>
              <div className="row overlay3">
                <div className="col-sm-12 text-center">
                    <button id="btnHire" onClick={() => { this.props.history.push("/postproject");}} className="btn btn-primary btn-md center-block" >I want to Hire</button>
                     <button id="btnWork" onClick={() => { this.props.history.push("/signup");}} className="btn btn-secondary btn-md center-block" >I want to Work</button>
                 </div>
              </div>
        </div>
    )
  }

}

export default withRouter(NavBeforeLogin);
