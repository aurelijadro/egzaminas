import React from "react";
import { Link } from "react-router-dom";
import { AppDataContext } from "../context";
import Axios from "axios";

export default function AdminPanel() {
  const appData = React.useContext(AppDataContext);
  const labels = appData.labels;
  const url = "http://localhost:8080";
  //const url = "http://localhost:8081/dovanos";

  if (labels === "loading") return <div>Loading...</div>;

  const labelList = labels.map((label, index) => {
    function deleteLabel() {
      Axios.delete(`${url}/api/labels/${label.id}`).then(
        appData.refreshProducts
      );
    }

    return (
      <div className="row my-1" key={label.id}>
        <div className="col-2">{index + 1}</div>
        <div className="col-2">
          <Link to={`/admin/labels/${label.id}`}>
            <img src={label.logo} className="img-fluid" alt="" />
          </Link>
        </div>
        <Link className="col-3" to={`/admin/labels/${label.id}`}>
          {label.title}
        </Link>
        <button className="col-2 btn btn-danger" onClick={deleteLabel}>
          Delete record label
        </button>
        <hr></hr>
      </div>
    );
  });
  return (
    <div className="container my-2">
      <Link className="btn btn-info my-2" to="/admin/labels/new">
        Add new Label
      </Link>
      <div className="row">
        <div className="col-2">#</div>
        <div className="col-2">Logo</div>
        <div className="col-6">Label</div>
        <div className="col-2"></div>
      </div>
      <div>{labelList}</div>
    </div>
  );
}
