import React from "react";
import { AppDataContext } from "../context";
import Axios from "axios";

class ProductAdministrationComponent extends React.Component {
  static contextType = AppDataContext;

  constructor(props, context) {
    super(props, context);

    const isCreateMode = props.match.path === "/admin/labels/new";
    this.state = {
      mode: isCreateMode ? "create" : "edit-loading",
      title: "",
      logo: "",
      category: "",
      size: "",
      url: "http://localhost:8080"
      //url: "http://localhost:8081/dovanos"
    };
  }

  componentDidMount() {
    this.labelsMayHaveLoaded();
  }

  componentDidUpdate() {
    this.labelsMayHaveLoaded();
  }

  labelsMayHaveLoaded() {
    if (this.context.labels === "loading") return;
    if (this.state.mode !== "edit-loading") return;
    const label = this.context.labels.find(
      label => String(label.id) === this.props.match.params.id
    );
    if (!label) {
      throw new Error("Label not found");
    }
    this.setState({
      mode: "edit",
      title: label.title,
      logo: label.logo,
      category: label.category,
      size: label.size
    });
  }

  handleTitleChange = event => this.setState({ title: event.target.value });
  handleLogoChange = event => this.setState({ logo: event.target.value });
  handleCategoryChange = event =>
    this.setState({ category: event.target.value });
  handleSizeChange = event => this.setState({ size: event.target.value });

  saveLabel = () => {
    this.setState({ mode: "edit-saving" });

    Axios.put(`${this.state.url}/api/labels/${this.props.match.params.id}`, {
      title: this.state.title,
      logo: this.state.logo,
      category: this.state.category,
      size: this.state.size
    })
      .then(this.context.refreshProducts)
      .then(() => this.props.history.push("/admin"));
  };

  createLabel = () => {
    Axios.post(`${this.state.url}/api/labels`, {
      title: this.state.title,
      logo: this.state.logo,
      category: this.state.category,
      size: this.state.size
    })
      .then(this.context.refreshProducts)
      .then(() => this.props.history.push("/admin"));
  };

  handleSubmit = event => {
    event.preventDefault();

    if (this.state.mode === "edit") {
      this.saveLabel();
    } else if (this.state.mode === "create") {
      this.createLabel();
    } else if (this.state.mode === "edit-loading") {
      throw new Error("Not supposed to be possible to submit in edit-loading");
    } else {
      throw new Error(`Unfamiliar mode ${this.state.mode}`);
    }
  };

  render() {
    if (this.state.mode === "edit-loading") {
      return (
        <div className="container">
          <p>Preparing the edit screen...</p>
        </div>
      );
    }

    if (this.state.mode === "edit-saving") {
      return <div>Saving changes...</div>;
    }

    return (
      <div className="container my-2">
        <div className="row">
          <div className="col-4">
            <form onSubmit={this.handleSubmit}>
              <div className="form-group">
                <label>
                  Title
                  <input
                    type="text"
                    required="required"
                    className="form-control"
                    value={this.state.title}
                    onChange={this.handleTitleChange}
                  />
                </label>
              </div>
              <div className="form-group">
                <label>
                  Logo url
                  <input
                    type="url"
                    id="logo"
                    required="required"
                    className="form-control"
                    value={this.state.logo}
                    onChange={this.handleLogoChange}
                  />
                </label>
              </div>
              <div className="form-group">
                <label>
                  Category (choose: National / Home / Live)
                  <input
                    type="text"
                    required="required"
                    className="form-control"
                    value={this.state.category}
                    onChange={this.handleCategoryChange}
                  />
                </label>
              </div>
              <div className="form-group">
                <label>
                  Size (choose: small / medium / large)
                  <input
                    type="text"
                    required="required"
                    className="form-control"
                    value={this.state.size}
                    onChange={this.handleSizeChange}
                  />
                </label>
              </div>

              <button type="submit" className="btn btn-info">
                Save
              </button>
              <button
                className="btn btn-light mx-2"
                onClick={() => this.props.history.push("/admin")}
              >
                Discard
              </button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default ProductAdministrationComponent;
