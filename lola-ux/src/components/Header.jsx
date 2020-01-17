import React from "react";
import "../App.css";

export default function Header() {
  return (
    <div className="jumbotron jumbotron-fluid p-4 my-0 text-white  bg-image ">
      <div className="col-md-6 px-0">
        <h1 className="display-2">Record Label Factory</h1>
        <p className="lead my-3">
          Services to find new songs, amazing artists and fantastic record
          labels.
        </p>
      </div>
    </div>
  );
}
