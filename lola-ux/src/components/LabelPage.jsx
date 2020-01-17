import React from "react";
import { AppDataContext } from "../context";
import { Link, Redirect } from "react-router-dom";

export default function LabelPage(props) {
  const appData = React.useContext(AppDataContext);
  const labels = appData.labels;

  if (labels === "loading") return "Loading";

  const label = labels.find(
    label => String(label.id) === props.match.params.id
  );
  if (!label) {
    return <Redirect to="/" />;
  }

  return (
    <div className="container row">
      <div className="col-4">
        <img src={label.logo} className="img-fluid" alt={label.title}></img>
      </div>
      <div className="col-8">
        <h1>{label.title}</h1>
        <h4>{label.category}</h4>
        <h4>Size: {label.size}</h4>
        <Link className="btn btn-light" to="/">
          Back
        </Link>
      </div>
      <h2 className="my-2">{label.title} manages these artists:</h2>
    </div>
  );
}
