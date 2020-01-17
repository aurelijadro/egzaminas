import React from "react";
import { Link } from "react-router-dom";

class CardComponent extends React.Component {
  render() {
    return (
      <div className="card mx-2" style={{ width: "13rem" }}>
        <img
          src={this.props.label.logo}
          className="card-img-top img-fluid"
          alt=""
        />
        <div className="card-body">
          <h5 className="card-title betterText">{this.props.label.title}</h5>
          <p className="card-text m-0 p-0 betterText">
            {this.props.label.category}
          </p>
          <div className="text-center">
            <Link
              className="btn btn-primary my-1"
              to={`/labels/${this.props.label.id}`}
            >
              More info
            </Link>
          </div>
        </div>
      </div>
    );
  }
}

export default CardComponent;
