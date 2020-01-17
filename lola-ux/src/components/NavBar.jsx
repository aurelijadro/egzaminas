import React from "react";
import { Link } from "react-router-dom";

export const Nav = function(props) {
  return (
    <nav className=" mb-2 blue">
      <ul className="nav row mx-auto justify-content-center ">
        <li className="nav-item col-2">
          <Link className="nav-link" to="/">
            Record Labels
          </Link>
        </li>
        <li className="nav-item col-2">
          <Link className="nav-link" to="/artists">
            Artists
          </Link>
        </li>
        <li className="nav-item col-2">
          <Link className="nav-link" to="/songs">
            Songs
          </Link>
        </li>
        <li className="nav-item col-2">
          <Link className="nav-link" to="/admin">
            Admin
          </Link>
        </li>
        <li className="col-4"></li>
        <li className="nav-item col-2"></li>
      </ul>
    </nav>
  );
};
