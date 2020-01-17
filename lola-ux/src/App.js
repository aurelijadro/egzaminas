import React, { useEffect, useState } from "react";
import ProductListComponent from "./components/List";
import ProductPage from "./components/LabelPage";
import AdminPanel from "./components/AdminPanel";
import ProductAdministrationComponent from "./components/Administration";
import ComingSoon from "./components/ComingSoon";
import "./App.css";
import { Nav } from "./components/NavBar";
import { serverLabelToClientLabel } from "./model/labels";
import { Switch, Route } from "react-router";
import { AppDataContext } from "./context";
import Header from "./components/Header";
import CommingSoon from "./components/ComingSoon";

function App() {
  const [labels, setLabels] = useState("loading");

  const url = "http://localhost:8080";
  //const url = "http://localhost:8081/dovanos";

  const refreshProducts = () => {
    fetch(url + "/api/labels")
      .then(res => {
        if (!res.ok) throw new Error(`response status ${res.status}`);
        return res.json();
      })
      .then(label => {
        setLabels(label.map(serverLabelToClientLabel));
      });
  };

  const appData = {
    labels: labels,
    refreshProducts: refreshProducts,
    setDovanos: setLabels
  };

  useEffect(refreshProducts, []);

  return (
    <AppDataContext.Provider value={appData}>
      <Header />
      <Nav />
      <div className="container mx-auto">
        <Switch>
          <Route path="/" exact component={ProductListComponent} />
          <Route path="/labels" exact component={ProductListComponent} />
          <Route path="/artists" exact component={ComingSoon} />
          <Route path="/songs" exact component={ComingSoon} />
          <Route path="/labels/:id" exact component={ProductPage} />
          <Route path="/admin" exact component={AdminPanel} />
          <Route
            path="/admin/labels/new"
            exact
            component={ProductAdministrationComponent}
          />
          <Route
            path="/admin/labels/:id"
            exact
            component={ProductAdministrationComponent}
          />
        </Switch>
      </div>
    </AppDataContext.Provider>
  );
}

export default App;
